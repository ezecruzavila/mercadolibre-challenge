OBSERVACIONES SOBRE EL EJERCICIO:

- La app está deployada en Heroku, la URL base es http://meli-tracer.herokuapp.com/api/ y los endpoints son POST:// ../api/trace y GET:// ../api/trace/stats

- Para la DB use Postgre de Heroku y los datos persistidos en la DB son los mínimos necesarios para la resolución del ejercicio, lo que fuera a almacenarse dependería de la lógica
   de negocio.

- La app se puede levantar en Docker ejecutando por linea de comando "docker-compose up --build" y corre en el puerto 1001. (ej: http://localhost:1001/api/trace)

- Para la documentacion de la API utilicé Swagger el cual está disponible en http://meli-tracer.herokuapp.com/api/swagger-ui/index.html

- Distancia a Bs As calculada en base a la diferencia de latitud y longitud entre Bs As y los del país en cuestión provistas por http://restcountries.eu/
   Asumo que los valores devueltos por la API son los de la capital del pais.

- Agrupé los llamados a las APIs externas en un solo service ya que era estrictamente un endpoint por API. En caso de escalar la app y tratarse de distintos llamados
   a una misma API probablemente los separaría en un service por cada uno y uno que agrupe sus llamados.

- Lo mismo ocurre con algunos DTOs contienen un solo campo. Parecen redundantes pero están hechos de este modo sobre la base de que se puedan recibir más
   parametros a futuro.

- Para el manejo del pool de conexiones utilicé valores random.
