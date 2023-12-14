package  proyectofinal;
import proyectofinal.Constants.{Oraculo, alfabeto};
import proyectofinal.Trie.Trie;



class ReconstruirCadenas {

    /**
      * Funcion que genera todas las cadenas de ADN de longitud n y comprueba si son aceptadas por el oráculo.
      * 
      * @param n Longitud de las cadenas a reconstruir.
      * @param o Oráculo que acepta o rechaza cadenas.
      * @return Conjunto de cadenas de ADN de longitud n que son aceptadas por el oráculo.
      */
    def reconstruirCadenasIngenuo(n: Int, o: Oraculo): Seq[Char] = {
        def generarCadenas(n: Int): Seq[Seq[Char]] = {
            if (n == 0) Seq(Seq())
            else for {
            letra <- alfabeto
            cadena <- generarCadenas(n - 1)
            } yield cadena :+ letra;
        }
       generarCadenas(n).find(o).getOrElse(Seq());
    }

    
   /**
     * Funcion que construye las subcadenas que son aceptadas por el oráculo para reconstruir la cadena de ADN de longitud n.
     *
     * @param n
     * @param o
     * @return Cadena que se esta buscando.
     */
     def reconstruirCadenasMejorado(n: Int, o: Oraculo): Seq[Char] = {
         val SC = alfabeto.map(Seq(_)).filter(w => o(w));
         def generarCombinaciones(cadenas: Seq[Seq[Char]], n: Int): Seq[Seq[Char]] = {
             if (n == 1) cadenas
             else {
             val nuevasCadenas = for {
                 s1 <- cadenas
                 s2 <- SC
             } yield s1 ++ s2
             generarCombinaciones(nuevasCadenas.filter(w => o(w)), n - 1);
             }
         }
         generarCombinaciones(SC, n).head;
    }

    /**
      * 
      * 
      * @param n Longitud de las cadenas a reconstruir.
      * @param o Oráculo que acepta o rechaza cadenas.
      * @return Cadena que se esta buscando.
      */

   def reconstruirCadenasTurbo(n: Int, o: Oraculo): Seq[Char] = {
        val SC = alfabeto.map(Seq(_)).filter(w => o(w));
        def generarCombinaciones(cadenas: Seq[Seq[Char]], n: Int): Seq[Seq[Char]] = {
            if (n <= 1) cadenas
            else {
                val nuevasCadenas = for {
                    s1 <- cadenas
                    s2 <- cadenas
                } yield s1 ++ s2;
                println(nuevasCadenas.length);
                generarCombinaciones(nuevasCadenas.filter(w => o(w)), n / 2);
            }
        }
        generarCombinaciones(SC, n).head;
    }
    /**
      * Funcion que construye las subcadenas que son aceptadas por el oráculo filtrando por aquellas que no esten 
      * en las cadenas candidatas
      * 
      * @param n Longitud de las cadenas a reconstruir.
      * @param o Oráculo que acepta o rechaza cadenas.
      * @return Cadena que se esta buscando.
      */
    def reconstruirCadenasTurboMejorada(n: Int, o: Oraculo): Seq[Char] = {
        val SC = alfabeto.map(Seq(_)).filter(w => o(w));

        def filtrar(cadenasOriginales: Seq[Seq[Char]], nuevasCadenas: Seq[Seq[Char]]): Seq[Seq[Char]] = {
            if (nuevasCadenas.head.length == 2 ) nuevasCadenas;
            else
            nuevasCadenas.filter { nuevaCadena =>
                val pares = nuevaCadena.sliding(nuevaCadena.length / 2, 1).toList
                pares.forall(cadenasOriginales.contains)
            }
        }  
        def generarCombinaciones(cadenas: Seq[Seq[Char]], n: Int): Seq[Seq[Char]] = {
            if (n <= 1) cadenas
            else {
                val nuevasCadenas = for {
                    s1 <- cadenas
                    s2 <- cadenas
                } yield s1 ++ s2;
                val filtrado = filtrar(cadenas, nuevasCadenas);
                generarCombinaciones(filtrado.filter(w => o(w)), n / 2);
            }
        }
        generarCombinaciones(SC, n).head;
     
    }

   def reconstruirCadenasTurboAcelerado(n: Int, o: Oraculo): Seq[Char] = {
        val SC = alfabeto.map(Seq(_)).filter(w => o(w))

        def filtrar(cadenasOriginales: Trie, nuevasCadenas: Seq[Seq[Char]]): Seq[Seq[Char]] = {
            if (nuevasCadenas.head.length == 2) nuevasCadenas;
            else
            nuevasCadenas.filter { nuevaCadena =>
                val pares = nuevaCadena.sliding(nuevaCadena.length / 2, 1).toList;
                pares.forall(cadena => Trie.cabezas(cadenasOriginales).contains(cadena.head));
            }
        }

        def generarCombinaciones(cadenas: Seq[Seq[Char]], n: Int): Seq[Seq[Char]] = {
            if (n <= 1) cadenas;
            else {
            val nuevasCadenas = for {
                s1 <- cadenas
                s2 <- cadenas
            } yield s1 ++ s2
            val filtrado = filtrar(Trie.Nodo(' ', false, SC.map(cadena => Trie.Hoja(cadena.head, true))), nuevasCadenas);
            generarCombinaciones(filtrado.filter(w => o(w)), n / 2);
            }
        }
        generarCombinaciones(SC, n).head
    }
}