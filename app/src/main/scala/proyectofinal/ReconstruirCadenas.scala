package  proyectofinal;
import proyectofinal.Types.Oraculo;
import proyectofinal.Types.alfabeto;



class ReconstruirCadenas {

    /**
      * Función que reconstruye la cadena de ADN de longitud n que son aceptadas por el oráculo.
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
     * Función que reconstruye la cadena de ADN de longitud n que son aceptadas por el oráculo.
     *
     * @param n
     * @param o
     * @return Cadena que se esta buscando.
     */
    def reconstruirCadenasMejorado(n: Int, o: Oraculo): Seq[Char] = {
         def generarCadenas(k: Int, SC: Seq[Seq[Char]]): Seq[Seq[Char]] = {
            if (k > n) SC
            else {
            val SCk = SC.flatMap(s => alfabeto.map(a => s :+ a)).filter(o);
            generarCadenas(k + 1, SCk);
            }
        }

        val SC = generarCadenas(1, Seq(Seq()));
        SC.find(_.length == n).getOrElse(Seq());
    }

    /**
      * Función que reconstruye la cadena de ADN de longitud n que son aceptadas por el oráculo.
      * 
      * @param n Longitud de las cadenas a reconstruir.
      * @param o Oráculo que acepta o rechaza cadenas.
      * @return Cadena que se esta buscando.
      */

    def reconstruirCadenasTurbo(N: Int, o: oraculo): Seq[Char] = {
        def generarCadenas(k: Int, SC: Seq[Seq[Char]]): Seq[Seq[Char]] = {
            if (k > N) SC
            else generarCadenas(k + 1, SC.flatMap(s => alfabeto.map(a => s :+ a)).filter(o))
        }

        generarCadenas(2, alfabeto.map(Seq(_)))
          .find(w => o(w))
          .getOrElse(Seq.empty)
    }



    /**
      * 
      * 
      * @param n Longitud de las cadenas a reconstruir.
      * @param o Oráculo que acepta o rechaza cadenas.
      * @return Cadena que se esta buscando.
      */
    def reconstruirCadenasTurboAcelerada(n: Int, o: oraculo): Seq[Char] = {

        def filtrar(SC: Seq[Seq[Char]], k: Int): Seq[Seq[Char]] = {
            var F = Seq.empty[Seq[Char]]
            for {
                s1 <- SC
                s2 <- SC
                s = s1 ++ s2
                if (1 to k).forall(i => SC.exists(w => w.take(i) == s.take(i)))
            } {
                F = F :+ s
            }
            F
        }

        def generarCadenas(k: Int, SC: Seq[Seq[Char]]): Seq[Seq[Char]] = {
            if (k > n) SC
            else {
                val SCk = filtrar(SC, k).filter(o)
                generarCadenas(k + 1, SCk)
            }
        }

        val SC = alfabeto.map(Seq(_))
        generarCadenas(2, SC)
          .find(o)
          .getOrElse(Seq.empty)
    }

}

