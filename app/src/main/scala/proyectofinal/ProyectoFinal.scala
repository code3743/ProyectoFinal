package proyectofinal;
import org.scalameter.measure
import org.scalameter.withWarmer
import org.scalameter.Warmer
import proyectofinal.Constants.{Oraculo, alfabeto, generarCadenaAleatoria, oraculo};
import proyectofinal.Trie.{Trie, Hoja, Nodo};


object ProyectoFinal {

  def main(args: Array[String]): Unit = {

    val benchmark = new Benchmark();
    val reconstruirCadenas = new ReconstruirCadenas();
    val reconstruirCadenasPar = new ReconstruirCadenasPar();
    println(
      withWarmer(new Warmer.Default) measure {
        (1 to 100000000).toArray
      }
    );

  //   for {
  //     i <- 1 to 10
  //     n = math.pow(2, i).toInt
  //     secuencia = generarCadenaAleatoria(n, alfabeto)
  //   } yield {
  //       val ( secuencial, paralelo, aceleracion ) = 
  //         benchmark.compararAlgoritmos(reconstruirCadenas.reconstruirCadenasMejorado,
  //             reconstruirCadenas.reconstruirCadenasTurbo)(n, oraculo(secuencia));
  //       println("Cadenas de " + n + " Caracteres");
  //       println("Tiempo secuencial: " + secuencial);
  //       println("Tiempo paralelo: " + paralelo);
  //       println("Aceleracion: " + aceleracion);
  //   }

  }

}

