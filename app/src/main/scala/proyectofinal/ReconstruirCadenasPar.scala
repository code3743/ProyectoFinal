package proyectofinal;
import common._;
import proyectofinal.Constants.{Oraculo, alfabeto};
import proyectofinal.Trie.Trie;     

class ReconstruirCadenasPar {

    def reconstruirCadenasIngenuo(n: Int, o: Oraculo): Seq[Char] = {
        def generarCadenas(n: Int): Seq[Seq[Char]] = {
            if (n == 0) Seq(Seq());
            else {
                val (parte1, parte2) = parallel(
                    for {
                        letra <- alfabeto.take(alfabeto.length / 2)
                        cadena <- generarCadenas(n - 1)
                    } yield cadena :+ letra,
                    for {
                        letra <- alfabeto.drop(alfabeto.length / 2)
                        cadena <- generarCadenas(n - 1)
                    } yield cadena :+ letra
                );
                parte1 ++ parte2;
            }
        }
        generarCadenas(n).find(o).getOrElse(Seq());
    }

   def reconstruirCadenasMejorado(n: Int, o: Oraculo): Seq[Char] = {
        val SC = alfabeto.map(Seq(_)).filter(w => o(w));
        def generarCombinaciones(cadenas: Seq[Seq[Char]], n: Int): Seq[Seq[Char]] = {
            if (n == 1) cadenas
            else {
                val (parte1, parte2) = parallel(
                    for {
                        s1 <- cadenas.take(cadenas.length / 2)
                        s2 <- SC
                    } yield s1 ++ s2,
                    for {
                        s1 <- cadenas.drop(cadenas.length / 2)
                        s2 <- SC
                    } yield s1 ++ s2
                );
                val nuevasCadenas = (parte1 ++ parte2).filter(w => o(w));
                generarCombinaciones(nuevasCadenas, n - 1);
            }
        }
        generarCombinaciones(SC, n).head;
    }

    def reconstruirCadenasTurbo(n: Int, o: Oraculo): Seq[Char] = {
        val SC = alfabeto.map(Seq(_)).filter(w => o(w))

        def generarCombinaciones(cadenas: Seq[Seq[Char]], n: Int): Seq[Seq[Char]] = {
            if (n <= 1) cadenas
            else {
            val mitad = cadenas.length / 2
            val (nuevasCadenasA, nuevasCadenasB) = parallel(
                for {
                s1 <- cadenas.take(mitad)
                s2 <- cadenas
                } yield s1 ++ s2,
                for {
                s1 <- cadenas.drop(mitad)
                s2 <- cadenas
                } yield s1 ++ s2
            )
            val nuevasCadenas = nuevasCadenasA ++ nuevasCadenasB
            generarCombinaciones(nuevasCadenas.filter(w => o(w)), n / 2)
            }
        }

        generarCombinaciones(SC, n).head
    }

    def reconstruirCadenasTurboMejorada(n: Int, o: Oraculo): Seq[Char] = {
        val SC = alfabeto.map(Seq(_)).filter(w => o(w));
        def filtrar(cadenasOriginales: Seq[Seq[Char]], nuevasCadenas: Seq[Seq[Char]]): Seq[Seq[Char]] = {
            if (nuevasCadenas.head.length == 2 ) nuevasCadenas
            else
            nuevasCadenas.filter { nuevaCadena =>
                val pares = nuevaCadena.sliding(nuevaCadena.length / 2, 1).toList
                pares.forall(cadenasOriginales.contains)
            }
        }  
        def generarCombinaciones(cadenas: Seq[Seq[Char]], n: Int): Seq[Seq[Char]] = {
            if (n <= 1) cadenas;
            else {
                val (parte1, parte2) = parallel(
                    for {
                        s1 <- cadenas.take(cadenas.length / 2)
                        s2 <- cadenas
                    } yield s1 ++ s2,
                    for {
                        s1 <- cadenas.drop(cadenas.length / 2)
                        s2 <- cadenas
                    } yield s1 ++ s2
                );
                val nuevasCadenas = parte1 ++ parte2;
                val filtrado = filtrar(cadenas, nuevasCadenas);
                generarCombinaciones(filtrado.filter(w => o(w)), n / 2);
            }
        }
        generarCombinaciones(SC, n).head;
    }

    def reconstruirCadenasTurboAcelerado(n: Int, o: Oraculo): Seq[Char] = {
        val SC = alfabeto.map(Seq(_)).filter(w => o(w))

        def filtrar(cadenasOriginales: Trie, nuevasCadenas: Seq[Seq[Char]]): Seq[Seq[Char]] = {
            if (nuevasCadenas.head.length == 2) nuevasCadenas
            else
            nuevasCadenas.filter { nuevaCadena =>
                val pares = nuevaCadena.sliding(nuevaCadena.length / 2, 1).toList
                pares.forall(cadena => Trie.cabezas(cadenasOriginales).contains(cadena.head))
            }
        }

        def generarCombinaciones(cadenas: Seq[Seq[Char]], n: Int): Seq[Seq[Char]] = {
            if (n <= 1) cadenas
            else {
                val (parte1, parte2) = parallel(
                    for {
                        s1 <- cadenas.take(cadenas.length / 2)
                        s2 <- cadenas
                    } yield s1 ++ s2,
                    for {
                        s1 <- cadenas.drop(cadenas.length / 2)
                        s2 <- cadenas
                    } yield s1 ++ s2
                )
                val nuevasCadenas = parte1 ++ parte2
                val filtrado = filtrar(Trie.Nodo(' ', false, SC.map(cadena => Trie.Hoja(cadena.head, true))), nuevasCadenas)
                generarCombinaciones(filtrado.filter(w => o(w)), n / 2)
            }
        }
        generarCombinaciones(SC, n).head
    }
}