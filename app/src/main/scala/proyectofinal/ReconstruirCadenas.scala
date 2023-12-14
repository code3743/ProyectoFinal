package  proyectofinal;
import proyectofinal.Constants.{Oraculo, alfabeto};



class ReconstruirCadenas {

    /**
      * Funcion que genera todas las cadenas de ADN de longitud n y comprueba si son aceptadas por el oráculo.
      * 
      * @param n Longitud de las cadenas a reconstruir.
      * @param o Oráculo que acepta o rechaza cadenas.
      * @return Conjunto de cadenas de ADN de longitud n que son aceptadas por el oráculo.
      */
    def reconstruirCadenasIngenuo(n: Int, o: Oraculo): Seq[Char] = {
        def generarCadenas(n: Int): Seq[Seq[Char]] = {
            if (n == 0) Seq(Seq())
            else for {
            letra <- alfabeto
            cadena <- generarCadenas(n - 1)
            } yield cadena :+ letra;
        }
       generarCadenas(n).find(o).getOrElse(Seq());
    }

    
   /**
     * Funcion que construye las subcadenas que son aceptadas por el oráculo para reconstruir la cadena de ADN de longitud n.
     *
     * @param n
     * @param o
     * @return Cadena que se esta buscando.
     */
     def reconstruirCadenasMejorado(n: Int, o: Oraculo): Seq[Char] = {
         val SC = alfabeto.map(Seq(_)).filter(w => o(w));
         def generarCombinaciones(cadenas: Seq[Seq[Char]], n: Int): Seq[Seq[Char]] = {
             if (n == 1) cadenas
             else {
             val nuevasCadenas = for {
                 s1 <- cadenas
                 s2 <- SC
             } yield s1 ++ s2
             generarCombinaciones(nuevasCadenas.filter(w => o(w)), n - 1);
             }
         }
         generarCombinaciones(SC, n).head;
    }

    /**
      * 
      * 
      * @param n Longitud de las cadenas a reconstruir.
      * @param o Oráculo que acepta o rechaza cadenas.
      * @return Cadena que se esta buscando.
      */

   def reconstruirCadenasTurbo(n: Int, o: Oraculo): Seq[Char] = {
        val SC = alfabeto.map(Seq(_)).filter(w => o(w))
        def generarCombinaciones(cadenas: Seq[Seq[Char]], n: Int): Seq[Seq[Char]] = {
            if (n <= 1) cadenas
            else {
                val nuevasCadenas = for {
                    s1 <- cadenas
                    s2 <- cadenas
                } yield s1 ++ s2;
                generarCombinaciones(nuevasCadenas.filter(w => o(w)), n / 2);
            }
        }
        generarCombinaciones(SC, n).head;
    }
    /**
      * 
      * 
      * @param n Longitud de las cadenas a reconstruir.
      * @param o Oráculo que acepta o rechaza cadenas.
      * @return Cadena que se esta buscando.
      */
    def generarCombinaciones(cadenas: Seq[Seq[Char]], n: Int): Seq[Seq[Char]] = {
        // implementar 
        Seq();
    }
}