package proyectofinal;
import scala.annotation.tailrec
import proyectofinal.Constants.{Oraculo, alfabeto};
import proyectofinal.Trie.{Trie, Nodo, Hoja, construirTrie, buscar};

class ReconstruirCadenas {

  /** Funcion que genera todas las cadenas de ADN de longitud n y comprueba si
    * son aceptadas por el oráculo.
    *
    * @param n
    *   Longitud de las cadenas a reconstruir.
    * @param o
    *   Oráculo que acepta o rechaza cadenas.
    * @return
    *   Conjunto de cadenas de ADN de longitud n que son aceptadas por el
    *   oráculo.
    */
  def reconstruirCadenasIngenuo(n: Int, o: Oraculo): Seq[Char] = {
    def generarCadenas(n: Int): Seq[Seq[Char]] = {
      if (n == 0) Seq(Seq());
      else
        alfabeto.flatMap(letra =>
          generarCadenas(n - 1).map(cadena => cadena :+ letra)
        );
    }
    generarCadenas(n).find(o).getOrElse(Seq());
  }

  /** Funcion que construye las subcadenas que son aceptadas por el oráculo para
    * reconstruir la cadena de ADN de longitud n.
    *
    * @param n
    * @param o
    * @return
    *   Cadena que se esta buscando.
    */
  def reconstruirCadenasMejorado(n: Int, o: Oraculo): Seq[Char] = {
    val SC = alfabeto.map(Seq(_)).filter(w => o(w));
    def generarCombinaciones(
        cadenas: Seq[Seq[Char]],
        n: Int
    ): Seq[Seq[Char]] = {
      if (n == 1) cadenas;
      else {
        val nuevasCadenas =
          cadenas.flatMap(s1 => SC.map(s2 => s1 ++ s2));
        generarCombinaciones(nuevasCadenas.filter(w => o(w)), n - 1);
      }
    }
    generarCombinaciones(SC, n).head;
  }

  /** @param n
    *   Longitud de las cadenas a reconstruir.
    * @param o
    *   Oráculo que acepta o rechaza cadenas.
    * @return
    *   Cadena que se esta buscando.
    */

  def reconstruirCadenasTurbo(n: Int, o: Oraculo): Seq[Char] = {
    val SC = alfabeto.map(Seq(_)).filter(w => o(w));
    def generarCombinaciones(
        cadenas: Seq[Seq[Char]],
        n: Int
    ): Seq[Seq[Char]] = {
      if (n <= 1) cadenas
      else {
        val nuevasCadenas =
          cadenas.flatMap(s1 => cadenas.map(s2 => s1 ++ s2));
        generarCombinaciones(nuevasCadenas.filter(w => o(w)), n / 2);
      }
    }
    generarCombinaciones(SC, n).head;
  }

  /** Funcion que construye las subcadenas que son aceptadas por el oráculo
    * filtrando por aquellas que no esten en las cadenas candidatas
    *
    * @param n
    *   Longitud de las cadenas a reconstruir.
    * @param o
    *   Oráculo que acepta o rechaza cadenas.
    * @return
    *   Cadena que se esta buscando.
    */
  def reconstruirCadenasTurboMejorada(n: Int, o: Oraculo): Seq[Char] = {
    val SC = alfabeto.map(Seq(_)).filter(w => o(w));
    def generarCombinaciones(
        cadenas: Seq[Seq[Char]],
        n: Int
    ): Seq[Seq[Char]] = {
      if (n <= 1) cadenas
      else {
        val filtrado = cadenas.flatMap { s1 =>
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

  /** @param n
    *   Longitud de las cadenas a reconstruir.
    * @param o
    *   Oráculo que acepta o rechaza cadenas.
    * @return
    */
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
