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
| 2          | 0.300874   | 0.15677  | 1.9192      |
| 4          | 0.305433   | 0.280776 | 1.0878      |
| 8          | 0.383162   | 0.459798 | 0.8333      |
| 16         | 0.621087   | 1.098249 | 0.5655      |
| 32         | 4.005463   | 3.141332 | 1.2751      |
| 64         | 28.069219  | 22.124942| 1.2687      |
| 128        | 167.730235 | 184.135631| 0.9109    |
| 256        | 1432.29709 | 1424.846276| 1.0052   |

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
| 2          | 0.196475   | 0.284192 | 0.6913      |
| 4          | 0.858367   | 0.736925 | 1.1648      |
| 8          | 0.930594   | 1.099807 | 0.8461      |
| 16         | 0.945142   | 0.564005 | 1.6758      |
| 32         | 2.690569   | 5.332967 | 0.5045      |
| 64         | 19.060699  | 20.665398| 0.9223      |
| 128        | 167.805119 | 159.575925| 1.0516    |
| 256        | 1452.320442| 1436.825458| 1.0108   |

---

#### reconstruirCadenasTurboAcelerado vs reconstruirCadenasTurboAceleradoParalelo

| caracteres | secuencial | paralelo | aceleracion |
|------------|------------|----------|-------------|
| 2          | 0.230118   | 0.264394 | 0.8704      |
| 4          | 1.082663   | 1.146274 | 0.9445      |
| 8          | 1.209254   | 1.116147 | 1.0834      |
| 16         | 2.873695   | 2.589715 | 1.1097      |
| 32         | 9.485785   | 7.18482  | 1.3203      |
| 64         | 17.493042  | 17.561583| 0.9961      |
| 128        | 126.266924 | 125.190563| 1.0086    |
| 256        | 1046.744153| 1014.719677| 1.0316   |

---