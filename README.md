# Healthy-Delicius-Food-Finder
 
 ## Hola Soy JacobClol y les presento este proyecto [LinkedIn](https://www.linkedin.com/in/jacobcl/)

## Descripción 

###### Arquitectura

Esta aplicación consta de un buscador que consume las API https://developer.edamam.com/ para buscar recetas de cocina, los cuales se listan y se puede visualizar su detalle, actualmente solo se encuentra para consultas en ingles ya que en español la beta que indican no funciona correctamente.

En la construcción de esta aplicación se eligió utilizar el patrón de diseño MVVM combinado con una CLEAN ARCHITECTURE con la finalidad de ser escalable y más fácil de mantener para ello se creó el siguiente sistema de directorios:

![arquitectura_buscador_recetas_cocina](https://user-images.githubusercontent.com/32649079/222688567-973c15b9-bc2f-41c3-bba3-6fe0d902c83f.jpg)

Como podemos ver se eligió tres capas la de UI, dominio y data, donde en cada modelo se crea un MAPPERS permitiendo una mejor separación de las capas, con sus respectivos casos de uso y repositorios. De igual manera se toma la idea del patrón DATASOURCE para definir dos fuentes de datos, una remota utilizando RETROFIT para las llamadas al servidor y otra local utilizando ROOM como ORM para la gestión de la base de datos, en esta última se le permite al usuario guardar localmente los items favoritos. También van encontrar que para los llamados a las diferentes capas se utiliza CORRUTINAS y una inyección de dependencias con DAGGER HILT.

###### Frontend

Para la parte de UI se utiliza una única ACTIVITY y FRAGMENTS con la finalidad de utilizar el NAVIGATION VIEW, sin embargo se agrega una activity más para agregar un mapa usando el SDK de google map, este tipo de navegación esto mejora la transición entre vistas y se puede dar de una manera más sencilla el control de la pila a medida que se abre una nueva o se presiona hacia atrás, de igual forma el envío de parámetros con SAFE ARGS que unión con DAGGER HILT y el VIEW MODEL ayuda a controlar el estado de la vista y mantener su configuración de una forma muy fácil, por ejemplo, mantener los datos al momento de rotar la pantalla y evitar llamados innecesarios a los casos de uso.

Para mostrar las imágenes se utiliza la librería de GLIDE. 

###### Manejo de Errores

Para tener un control del lado del desarrollador se utiliza un Interceptor para el cliente HTTP que permite visualizar los objetos relacionados con las llamas al servidor. Esto es importante para poder gestionar las fallas que se presenten al momento de su uso y ayuda a generar cambios que mejoren la aplicación. Por el lado del usuario se utiliza de momento, mensajes TOAST que le den una realimentación de ciertos eventos que se desencadenan con la interacción de la UI, por ejemplo, cuando se realiza una búsqueda y no se obtiene algún resultado, o cuando se ha guardado un favorito exitosamente.

###### Testing

He agregado una serie de test utilizando MOCK que permiten verificar el funcionamiento de los casos de uso, repositorio y datasource, así como verificar que están actualizando los LIVEDATA del viewmodel para actualizar las vistas.

También se agregan de momento dos pruebas de instrumentación utilizando la herramientas que proporciona Dagger Hilt y se sigue el patrón de teste de una página, para ello se agregan las configuraciones necesarias.

###### Versionamiento

El proyecto utiliza consta de una ramas una main ya que todo se trabajó de manera local, sin embargo es bueno utilizar una rama develop que me permita agregar cambios sin perjudicar la versión estable que se encuentra en main.

Para finalizar recomiendo utilizar un plugin que convierte los JSON a las data class que se puede instalar en el Android Studio realmente ahorra mucho tiempo.

Y debido a que el API no proporciona ubicaciones de las recetas, su origen para una geocalización se crea un provider sencillo que toma un de una lista de ciudades un valor random y es agregado en el momento que se obtiene respuesta del servidor, con la finalidad de poder emular la búsqueda y colocación de un marcador en el mapa.
