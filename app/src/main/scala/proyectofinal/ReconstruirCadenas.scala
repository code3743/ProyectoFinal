package  proyectofinal;
import proyectofinal.Types.oraculo;
import proyectofinal.Types.alfabeto;



class ReconstruirCadenas {

    /**
      * Función que reconstruye la cadena de ADN de longitud n que son aceptadas por el oráculo.
      * 
      * @param n Longitud de las cadenas a reconstruir.
      * @param o Oráculo que acepta o rechaza cadenas.
      * @return Conjunto de cadenas de ADN de longitud n que son aceptadas por el oráculo.
      */

      // Algoritmo:
        // alfabeto = {a, c, g, t}
        // reconstruirCadenasIngenuo(n,  ΨS):
            
        //     foreach w perteneciente (alfabeto^n):
            //      do if  ΨS(w):
                        // then return w;

    def reconstruirCadenasIngenuo(n: Int, o: oraculo): Seq[Char] = {
        def generarCadenas(n: Int): Seq[Seq[Char]] = {
            if (n == 0) Seq(Seq())
            else for {
            letra <- alfabeto
            cadena <- generarCadenas(n - 1)
            } yield cadena :+ letra;
        }
        generarCadenas(n).find(o).getOrElse(Seq());
    }
    
    // reconstruirCadenasMejorado(N,  ΨS):
        // SC0 = { lambda  }
        // for k = 1 to N 
        //      do SCk  <- SCk−1 · alfabeto
        //      foreach w perteneciente (SCk):
        //          do if  ~ΨS(w):
        //              then SCk  <- SCk - {w}
        //          else if |w| = N:
        //              then return w;

    def reconstruirCadenasMejorado(n: Int, o: oraculo): Seq[Char] = {
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



    // Turbo solucion:
    // reconstruirCadenasTurbo(N,  ΨS):
     // SC1 <- alfabeto, k <- 2
    // while k <= N 
    //      do SCk  <- SCk/2 · SCk/2
    //      foreach w perteneciente (SCk):
    //          do if  ~ΨS(w):
    //              then SCk  <- SCk - {w}
    //          else if |w| = N:
    //              then return w;

    def reconstruirCadenasTurbo(n: Int, o: oraculo): Seq[Char] = {
    
        def generarCadenas(k: Int, SC: Seq[Seq[Char]]): Seq[Seq[Char]] = {
            if (k > n) SC;
            else {
            val SCk = SC.flatMap(s => SC.map(a => s :+ a)).filter(o);
            generarCadenas(k + 1, SCk);
            }
        }

        val SC = generarCadenas(2, alfabeto.map(Seq(_)));
        SC.find(o).getOrElse(Seq());
    }


    // Turbo acelerada:
    // Filtrar(SC, k):
    //     F ← ∅
    //     foreach s1, s2 perteneciente (SC):
    //         do s <- s1 . s2
    //         if { w : w  s, |w| = k } ⊆ SC:
    //             then F <- F ∪ {s}
    //     return F

    def reconstruirCadenasTurboMejorado(n: Int, o: oraculo): Seq[Char] = {

        def filtrar(SC: Seq[Seq[Char]], k: Int): Seq[Seq[Char]] = {
            SC.flatMap(s1 => SC.map(s2 => s1 ++ s2))
            .filter(s => s.sliding(k).forall(SC.contains));
        }

        def generarCadenas(k: Int, SC: Seq[Seq[Char]]): Seq[Seq[Char]] = {
            if (k > n) SC;
            else {
            val SCk = filtrar(SC, k).filter(o);
            generarCadenas(k + 1, SCk);
            }
        }

        val SC = generarCadenas(2, alfabeto.map(Seq(_)));
        SC.find(o).getOrElse(Seq());
    }

}
