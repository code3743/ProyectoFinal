package proyectofinal;
import proyectofinal.Constants.{Oraculo, alfabeto};

class RecustruirCadenasParUmbral {


    val reconstruirCadenasPar = new ReconstruirCadenasPar();
    val reconstruirCadenas = new ReconstruirCadenas();

    def reconstruirCadenasIngenuo(umbral:Int)(n: Int, o: Oraculo): Seq[Char] = {
        if (n < umbral) {
            reconstruirCadenas.reconstruirCadenasIngenuo(n, o);
        } else {
            reconstruirCadenasPar.reconstruirCadenasIngenuo(n, o);
        }
    }

    def reconstruirCadenasMejorado(umbral:Int)(n: Int, o: Oraculo): Seq[Char] = {
        if (n < umbral) {
            reconstruirCadenas.reconstruirCadenasMejorado(n, o);
        } else {
            reconstruirCadenasPar.reconstruirCadenasMejorado(n, o);
        }
    }

    def reconstruirCadenasTurbo(umbral:Int)(n: Int, o: Oraculo): Seq[Char] = {
        if (n <= umbral) {
            reconstruirCadenas.reconstruirCadenasTurbo(n, o);
        } else {
            reconstruirCadenasPar.reconstruirCadenasTurbo(n, o);
        }
    }

    def reconstruirCadenasTurboMejorada(umbral:Int)(n: Int, o: Oraculo): Seq[Char] = {
        if (n <= umbral) {
            reconstruirCadenas.reconstruirCadenasTurboMejorada(n, o);
        } else {
            reconstruirCadenasPar.reconstruirCadenasTurboMejorada(n, o);
        }
    }

    def reconstruirCadenasTurboAcelerado(umbral:Int)(n: Int, o: Oraculo): Seq[Char] = {
        if (n < umbral) {
            reconstruirCadenas.reconstruirCadenasTurboAcelerado(n, o);
        } else {
            reconstruirCadenasPar.reconstruirCadenasTurboAcelerado(n, o);
        }
    }
}