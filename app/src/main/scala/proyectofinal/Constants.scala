package proyectofinal;
import scala.util.Random;


object Constants {
    type Oraculo = Seq[Char] => Boolean;
    val alfabeto = Seq('a', 'c', 'g', 't');

    def generarCadenaAleatoria(n: Int, alfabeto: Seq[Char]): Seq[Char] = {
        Seq.fill(n)(alfabeto(Random.nextInt(alfabeto.size)))
    }
    
    def oraculo(cadena: Seq[Char]):Oraculo = {
       (secuencia: Seq[Char]) => cadena.containsSlice(secuencia)
    }
    
}
