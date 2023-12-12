package proyectofinal;

object Trie {
  abstract class Trie;
  case class Nodo (car: Char, marcado: Boolean, hijos: Seq[Trie]) extends Trie;
  case class Hoja (car: Char, marcado: Boolean) extends Trie;

  def raiz(t: Trie): Char = t match {
    case Nodo(car, marcado, hijos) => car;
    case Hoja(car, marcado) => car;
  }

  def cabezas(t: Trie): Seq[Char] = t match {
    case Nodo(car, marcado, hijos) => hijos.map(raiz);
    case Hoja(car, marcado) => Seq[Char](car);
  }
}