package proyectofinal;
import org.scalameter.measure
import org.scalameter.withWarmer
import org.scalameter.Warmer
import proyectofinal.Constants.{Oraculo, alfabeto, generarCadenaAleatoria, oraculo};


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
 
    for {
      i <- 1 to 8
      n = math.pow(2, i).toInt
      secuencia = generarCadenaAleatoria(n, alfabeto)
    } yield {
        val ( secuencial, paralelo, aceleracion ) = 
          benchmark.compararAlgoritmos(reconstruirCadenas.reconstruirCadenasTurboAcelerado,
              reconstruirCadenasPar.reconstruirCadenasTurboAcelerado)(n, oraculo(secuencia));
        println("|    " + n + "      | " + secuencial + " | " + paralelo + " | " + aceleracion + " |");
    }
  }
}

