# Proyecto final - Programación funcional y concurrente


## Integrantes
- [Pedro Bernal Londoño]()
- [Kevin Steven Ramírez]()
- [Jota E. López](https://github.com/code3743)


## Analisis general

Resolver el problema de reconstruir una cadena de ADN a partir de subcadenas es una tarea abordada mediante la implementación de cinco algoritmos distintos, cada uno con su propia complejidad. El objetivo principal es comparar los tiempos de ejecución de estos algoritmos en busca de eficiencias, aunque en teoría se busca reducir el costo de las operaciones, la complejidad de los algoritmos puede afectar este objetivo.

En el contexto del proyecto, se introduce el concepto de un "oráculo", conceptualizado como una entidad que posee conocimiento de la cadena objetivo y siempre proporciona respuestas correctas a preguntas específicas. Estas preguntas están formuladas para determinar si una cadena dada es subcadena de la cadena final buscada. Aunque la teoría sugiere que se debería minimizar el costo de las preguntas al oráculo, en la práctica, la complejidad de los algoritmos puede influir en el tiempo de ejecución, creando un dilema entre el costo computacional interno y el costo asociado con las consultas al oráculo.

Se ha identificado que, en términos de costo, es más eficiente realizar preguntas al oráculo en lugar de recorrer la secuencia directamente. Sin embargo, al referirse al costo computacional general, se hace hincapié en que se busca minimizar el costo asociado con las preguntas al oráculo. Se plantea la dualidad entre reducir la cantidad de preguntas al oráculo y aumentar el costo computacional interno, ya que la manipulación de cadenas y subcadenas internas implica recorrer nuevamente la secuencia, lo que está directamente relacionado con la cantidad de elementos en la secuencia.

### Comparación de tiempo de ejecución entre secuencial y paralelo

#### reconstruirCadenasIngenuo vs reconstruirCadenasIngenuoParalelo

| caracteres | secuencial | paralelo | aceleracion |
|------------|------------|----------|-------------|
| 2          | 0.420184   | 0.316476 | 1.3277      |
| 4          | 1.259649   | 1.2284   | 1.0254      |

---

#### reconstruirCadenasMejorado vs reconstruirCadenasMejoradoParalelo

| caracteres | secuencial | paralelo | aceleracion |
|------------|------------|----------|-------------|
|    2      | 0.088739 | 0.583951 | 0.15196309279374468 |
|    4      | 0.11554 | 0.242001 | 0.4774360436527122 |
|    8      | 0.56182 | 0.78815 | 0.7128338514242213 |
|    16      | 0.459324 | 1.310204 | 0.35057441436600717 |
|    32      | 8.609565 | 4.505334 | 1.9109715284149853 |
|    64      | 24.72874 | 23.412847 | 1.0562038866951977 |
|    128      | 204.497834 | 161.400146 | 1.267023847673595 |
|    256      | 1423.463447 | 1436.344615 | 0.9910319794668497 |

---

#### reconstruirCadenasTurbo vs reconstruirCadenasTurboParalelo

| caracteres | secuencial | paralelo | aceleracion |
|------------|------------|----------|-------------|
| 2          | 0.171257   | 0.138294 | 1.2384      |
| 4          | 0.164364   | 0.231652 | 0.7095      |
| 8          | 0.4085     | 0.390144 | 1.047       |
| 16         | 0.574696   | 0.38236  | 1.503       |
| 32         | 7.096034   | 2.515842 | 2.8205      |
| 64         | 25.671777  | 31.62619 | 0.8117      |
| 128        | 292.378292 | 265.519172| 1.1012    |
| 256        | 2652.137331| 2587.588671| 1.0249   |

---

#### reconstruirCadenasTurboMejorado vs reconstruirCadenasTurboMejoradoParalelo

| caracteres | secuencial | paralelo | aceleracion |
|------------|------------|----------|-------------|
|    2      | 0.088739 | 0.583951 | 0.15196309279374468 |
|    4      | 0.11554 | 0.242001 | 0.4774360436527122 |
|    8      | 0.56182 | 0.78815 | 0.7128338514242213 |
|    16      | 0.459324 | 1.310204 | 0.35057441436600717 |
|    32      | 8.609565 | 4.505334 | 1.9109715284149853 |
|    64      | 24.72874 | 23.412847 | 1.0562038866951977 |
|    128      | 204.497834 | 161.400146 | 1.267023847673595 |
|    256      | 1423.463447 | 1436.344615 | 0.9910319794668497 |


---

#### reconstruirCadenasTurboAcelerado vs reconstruirCadenasTurboAceleradoParalelo

| caracteres | secuencial | paralelo | aceleracion |
|------------|------------|----------|-------------|
|    2      | 0.088739 | 0.583951 | 0.15196309279374468 |
|    4      | 0.11554 | 0.242001 | 0.4774360436527122 |
|    8      | 0.56182 | 0.78815 | 0.7128338514242213 |
|    16      | 0.459324 | 1.310204 | 0.35057441436600717 |
|    32      | 8.609565 | 4.505334 | 1.9109715284149853 |
|    64      | 24.72874 | 23.412847 | 1.0562038866951977 |
|    128      | 204.497834 | 161.400146 | 1.267023847673595 |
|    256      | 1423.463447 | 1436.344615 | 0.9910319794668497 |


---