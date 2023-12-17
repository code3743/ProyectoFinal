package proyectofinal;
import common._;
import scala.annotation.tailrec
import proyectofinal.Constants.{Oraculo, alfabeto};
import proyectofinal.Trie.{Trie, Nodo, Hoja, construirTrie, buscar};

class ReconstruirCadenasPar {

  def reconstruirCadenasIngenuo(n: Int, o: Oraculo): Seq[Char] = {
    def generarCadenas(n: Int): Seq[Seq[Char]] = {
      if (n == 0) Seq(Seq());
      else {
        val size = alfabeto.length / 2;
        val (parte1, parte2) = parallel(
          alfabeto.take(size).flatMap(letra =>
            generarCadenas(n - 1).map(cadena => cadena :+ letra)
          ),
          alfabeto.drop(size).flatMap(letra =>
            generarCadenas(n - 1).map(cadena => cadena :+ letra)
          )
        );
        
        parte1 ++ parte2;
      }
    }
    generarCadenas(n).find(o).getOrElse(Seq());
  }

  def reconstruirCadenasMejorado(n: Int, o: Oraculo): Seq[Char] = {
    val SC = alfabeto.map(Seq(_)).filter(w => o(w));
    def generarCombinaciones(
        cadenas: Seq[Seq[Char]],
        n: Int
    ): Seq[Seq[Char]] = {
      if (n == 1) cadenas
      else {
        val size = cadenas.length / 2;  
        val (parte1, parte2) = parallel(
          cadenas.take(size).flatMap(s1 => SC.map(s2 => s1 ++ s2)),
          cadenas.drop(size).flatMap(s1 => SC.map(s2 => s1 ++ s2))
        );
       
        generarCombinaciones((parte1 ++ parte2).filter(w => o(w)) , n - 1);
      }
    }
    generarCombinaciones(SC, n).head;
  }

  def reconstruirCadenasTurbo(n: Int, o: Oraculo): Seq[Char] = {
    val SC = alfabeto.map(Seq(_)).filter(w => o(w))

    def generarCombinaciones(
        cadenas: Seq[Seq[Char]],
        n: Int
    ): Seq[Seq[Char]] = {
      if (n <= 1) cadenas
      else {
        val size = cadenas.length / 2
        val (parte1, parte2) = parallel(
         cadenas.take(size).flatMap(s1 => cadenas.map(s2 => s1 ++ s2)),
          cadenas.drop(size).flatMap(s1 => cadenas.map(s2 => s1 ++ s2))
        )
       
        generarCombinaciones((parte1 ++ parte2).filter(w => o(w)), n / 2)
      }
    }

    generarCombinaciones(SC, n).head
  }

  def reconstruirCadenasTurboMejorada(n: Int, o: Oraculo): Seq[Char] = {
    val SC = alfabeto.map(Seq(_)).filter(w => o(w));
    def generarCombinaciones(
        cadenas: Seq[Seq[Char]],
        n: Int
    ): Seq[Seq[Char]] = {
      if (n <= 1) cadenas;
      else {
        val filtrado =  cadenas.flatMap { s1 =>
                  cadenas.flatMap { s2 =>
                    val nuevaCadena = s1 ++ s2;
                    if (nuevaCadena.length == 2 || nuevaCadena.sliding(nuevaCadena.length / 2).forall(cadenas.contains)) {
                      Some(nuevaCadena);
                    } else None
                  }
        }
        generarCombinaciones(filtrado.filter(w => o(w)), n / 2);
      }
    }
    generarCombinaciones(SC, n).head;
  }
  def reconstruirCadenasTurboAcelerado(n: Int, o: Oraculo): Seq[Char] = {

    @tailrec
    def transformarCadena(
        s: Seq[Char],
        acc: Seq[Seq[Char]] = Seq.empty
    ): Seq[Seq[Char]] = s match {
      case Nil                    => acc
      case x :: xs if xs.nonEmpty => transformarCadena(xs, xs +: acc)
      case _                      => acc
    }

    val SC = alfabeto.map(Seq(_)).filter(w => o(w));
    def generarCombinaciones(
        cadenas: Seq[Seq[Char]],
        n: Int
    ): Seq[Seq[Char]] = {
      if (n <= 1) cadenas;
      else {
       val cadenasArboles = cadenas
          .map(lista => transformarCadena(lista, Seq() :+ lista))
          .flatten
          .sortBy(_.length);
        val arbol = construirTrie(cadenasArboles);
        val filtrado = cadenas.flatMap { s1 =>
                  cadenas.flatMap { s2 =>
                    val nuevaCadena = s1 ++ s2;
                    if (nuevaCadena.length == 2 || nuevaCadena.sliding(nuevaCadena.length / 2).forall(par => buscar(arbol, par))) {
                      Some(nuevaCadena);
                    } else None
                  }
                }
        generarCombinaciones(filtrado.filter(w => o(w)), n / 2);
      }
    }
    generarCombinaciones(SC, n).head;
  }
 
}
