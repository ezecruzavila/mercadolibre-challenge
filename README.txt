Para la resolución del ejercicio realicé las siguientes suposiciones:


1. Distancia a Bs As calculada en base a la diferencia de latitud y longitud entre Bs As y los del país en cuestión provistas por http://restcountries.eu/
   Asumo que los valores devueltos por la API son los de la capital del pais.

2- Agrupé los llamados a las APIs externas en un solo service ya que era estrictamente un endpoint por API. En caso de escalar la app y tratarse de distintos llamados
   a una misma API probablemente los separaría en un service por cada uno y uno que agrupe sus llamados.

3- Los datos persistidos en la DB son los mínimos necesarios para la resolución del ejercicio, lo que fuera a almacenarse dependería de la lógica
   de negocio.

4- Lo mismo ocurre con algunos DTOs contienen un solo campo. Parecen redundantes pero están hechos de este modo sobre la base de que se puedan recibir más
   parametros a futuro.

5- Para el manejo del pool de conexiones utilicé valores random.
