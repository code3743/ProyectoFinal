package proyectofinal

import scala.util.Random
import org.scalatest.funsuite.AnyFunSuite
import proyectofinal.Constants.{Oraculo, alfabeto, generarCadenaAleatoria, oraculo};

class ReconstruirCadenasTest extends AnyFunSuite {
    
    test("Test reconstruirCadenasIngenuo") {
        val reconstruirCadenas = new ReconstruirCadenas();
        val cadenaRandomN4 = generarCadenaAleatoria(4, alfabeto);
        
        val cadenaRandomN16 = generarCadenaAleatoria(16, alfabeto);
        val cadenaRandomN32 = generarCadenaAleatoria(32, alfabeto);
        val cadenaRandomN64 = generarCadenaAleatoria(64, alfabeto);
        val result4 = reconstruirCadenas.reconstruirCadenasIngenuo(4, oraculo(cadenaRandomN4));
        val result16 = reconstruirCadenas.reconstruirCadenasIngenuo(16, oraculo(cadenaRandomN16));
        val result32 = reconstruirCadenas.reconstruirCadenasIngenuo(32, oraculo(cadenaRandomN32));
        val result64 = reconstruirCadenas.reconstruirCadenasIngenuo(64, oraculo(cadenaRandomN64));
        assert(result4 == cadenaRandomN4);
   
    }

    test("Test recontruirCadenasMejorada"){
        val reconstruirCadenas = new ReconstruirCadenas();
        val cadenaRandomN4 = generarCadenaAleatoria(4, alfabeto);
        val cadenaRandomN16 = generarCadenaAleatoria(16, alfabeto);
        val cadenaRandomN32 = generarCadenaAleatoria(32, alfabeto);
        val cadenaRandomN64 = generarCadenaAleatoria(64, alfabeto);
        val result4 = reconstruirCadenas.reconstruirCadenasMejorado(4, oraculo(cadenaRandomN4));
        val result16 = reconstruirCadenas.reconstruirCadenasMejorado(16, oraculo(cadenaRandomN16));
        val result32 = reconstruirCadenas.reconstruirCadenasMejorado(32, oraculo(cadenaRandomN32));
        val result64 = reconstruirCadenas.reconstruirCadenasMejorado(64, oraculo(cadenaRandomN64));
        assert(result4 != cadenaRandomN4);
        assert(result16 == cadenaRandomN16);
        assert(result32 == cadenaRandomN32);
        assert(result64 == cadenaRandomN64);
    }

}
