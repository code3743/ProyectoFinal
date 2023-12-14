package proyectofinal;
import proyectofinal.Constants.{Oraculo, alfabeto, generarCadenaAleatoria, oraculo};


object ProyectoFinal {

  

  def main(args: Array[String]): Unit = {

    val reconstruirCadenas = new ReconstruirCadenas();
    
    // val cadenaRandomN4 = generarCadenaAleatoria(4, alfabeto);
    // val cadenaRandomN8 = generarCadenaAleatoria(8, alfabeto);
    val cadenaRandomN16 = generarCadenaAleatoria(16, alfabeto);
    // val cadenaRandomN32 = generarCadenaAleatoria(32, alfabeto);
    
    // val result4 = reconstruirCadenas.reconstruirCadenasTurbo(4, oraculo(cadenaRandomN4));
    // val result8 = reconstruirCadenas.reconstruirCadenasTurbo(8, oraculo(cadenaRandomN8));

    // val result16 = reconstruirCadenas.reconstruirCadenasTurbo(16, oraculo(cadenaRandomN16));
    
    val result16A = reconstruirCadenas.reconstruirCadenasTurboMejorada(16, oraculo(cadenaRandomN16));
    // val result32 = reconstruirCadenas.reconstruirCadenasTurbo(32, oraculo(cadenaRandomN32));
   
    // println(cadenaRandomN4);
    // println(result4 == cadenaRandomN4);
    // println(cadenaRandomN8);
    // println(result8 == cadenaRandomN8);
   

      // println(result16 == cadenaRandomN16);
    // println(cadenaRandomN32);
    // println(result32 == cadenaRandomN32);


    
  }
}


