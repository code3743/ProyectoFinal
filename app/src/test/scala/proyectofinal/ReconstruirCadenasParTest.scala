package proyectofinal
import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import proyectofinal.Constants.{Oraculo, alfabeto, generarCadenaAleatoria, oraculo};

@RunWith(classOf[JUnitRunner])
class ReconstruirCadenasParTest extends AnyFunSuite {
    val reconstruirCadenas = new ReconstruirCadenasPar();
    
    
    test("Test reconstruirCadenasIngenuo") {
        val cadenaRandomN4 = generarCadenaAleatoria(4, alfabeto);
        val result4 = reconstruirCadenas.reconstruirCadenasIngenuo(4, oraculo(cadenaRandomN4));
        assert(result4 == cadenaRandomN4);
   
    }

    test("Test recontruirCadenasMejorada"){
        val cadenaRandomN4 = generarCadenaAleatoria(4, alfabeto);
        val cadenaRandomN16 = generarCadenaAleatoria(16, alfabeto);
        val cadenaRandomN32 = generarCadenaAleatoria(32, alfabeto);
        val cadenaRandomN64 = generarCadenaAleatoria(64, alfabeto);
        val result4 = reconstruirCadenas.reconstruirCadenasMejorado(4, oraculo(cadenaRandomN4));
        val result16 = reconstruirCadenas.reconstruirCadenasMejorado(16, oraculo(cadenaRandomN16));
        val result32 = reconstruirCadenas.reconstruirCadenasMejorado(32, oraculo(cadenaRandomN32));
        val result64 = reconstruirCadenas.reconstruirCadenasMejorado(64, oraculo(cadenaRandomN64));
        assert(result4 == cadenaRandomN4);
        assert(result16 == cadenaRandomN16);
        assert(result32 == cadenaRandomN32);
        assert(result64 == cadenaRandomN64);
    }

     test("Test recontruirCadenasTurbo"){
        val cadenaRandomN4 = generarCadenaAleatoria(4, alfabeto);
        val cadenaRandomN16 = generarCadenaAleatoria(16, alfabeto);
        val cadenaRandomN32 = generarCadenaAleatoria(32, alfabeto);
        val cadenaRandomN64 = generarCadenaAleatoria(64, alfabeto);
        val result4 = reconstruirCadenas.reconstruirCadenasTurbo(4, oraculo(cadenaRandomN4));
        val result16 = reconstruirCadenas.reconstruirCadenasTurbo(16, oraculo(cadenaRandomN16));
        val result32 = reconstruirCadenas.reconstruirCadenasTurbo(32, oraculo(cadenaRandomN32));
        val result64 = reconstruirCadenas.reconstruirCadenasTurbo(64, oraculo(cadenaRandomN64));
        assert(result4 == cadenaRandomN4);
        assert(result16 == cadenaRandomN16);
        assert(result32 == cadenaRandomN32);
        assert(result64 == cadenaRandomN64);
    }

    test("Test recontruirCadenasTurboMejorada"){
        val cadenaRandomN4 = generarCadenaAleatoria(4, alfabeto);
        val cadenaRandomN16 = generarCadenaAleatoria(16, alfabeto);
        val cadenaRandomN32 = generarCadenaAleatoria(32, alfabeto);
        val cadenaRandomN64 = generarCadenaAleatoria(64, alfabeto);
        val result4 = reconstruirCadenas.reconstruirCadenasTurboMejorada(4, oraculo(cadenaRandomN4));
        val result16 = reconstruirCadenas.reconstruirCadenasTurboMejorada(16, oraculo(cadenaRandomN16));
        val result32 = reconstruirCadenas.reconstruirCadenasTurboMejorada(32, oraculo(cadenaRandomN32));
        val result64 = reconstruirCadenas.reconstruirCadenasTurboMejorada(64, oraculo(cadenaRandomN64));
        assert(result4 == cadenaRandomN4);
        assert(result16 == cadenaRandomN16);
        assert(result32 == cadenaRandomN32);
        assert(result64 == cadenaRandomN64);
    }

}
