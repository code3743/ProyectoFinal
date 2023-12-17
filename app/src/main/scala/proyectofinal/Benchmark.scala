package proyectofinal;
import proyectofinal.Constants.{Oraculo};
import org.scalameter.measure
import org.scalameter.withWarmer
import org.scalameter.Warmer
import org.scalameter.Quantity

class Benchmark {
    
    def compararAlgoritmos(Funcion1:(Int,Oraculo) => Seq[Char], Funcion2:(Int,Oraculo) => Seq[Char])(n: Int, o: Oraculo): (Double, Double, Double) = {
        val timeF1 = withWarmer(new Warmer.Default) measure {
            Funcion1(n, o);
        }
        val timeF2 = withWarmer(new Warmer.Default) measure {
            Funcion2(n, o);
        }

        val promedio = timeF1.value / timeF2.value;
        (timeF1.value, timeF2.value, promedio);
    }


    def tomarTiempoEjecucion(Funcion:(Int,Oraculo) => Seq[Char])(n: Int, o: Oraculo): Double = {
        val timeF = withWarmer(new Warmer.Default) measure {
            Funcion(n, o);
        }
        timeF.value;
    }
}