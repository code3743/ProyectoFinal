package  proyectofinal
import proyectofinal.Types.oraculo;
import  proyectofinal.ReconstruirCadenas;



object Main {

  def main(args: Array[String]): Unit = {
    val rci = new ReconstruirCadenas()

    val sequencia = Seq('t', 'g', 'a', 'a','t')
    val o: oraculo = (s: Seq[Char]) => {
      sequencia.containsSlice(s)
    }
    println(rci.reconstruirCadenasIngenuo(5, o))
    println(rci.reconstruirCadenasMejorado(5,o))
    println(rci.reconstruirCadenasTurbo(5,o))
    println(rci.reconstruirCadenasTurboAcelerada(5,o))

  }

}

