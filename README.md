# [Prueba de anuncios]

## Contenido
1. [Decisiones del desarrollo](#planteamiento-del-problema)
2. [Instrucciones de ejecución](#instrucciones-de-ejecución)
3. [Instrucciones de utilización](#instrucciones-de-utilización)




## Decisiones del desarrollo.

**1. El software ha sido desarrollado mediante el framework de Spring utilizando un proyecto de tipo Spring-boot.**
    - He decidido utilizar Spring-boot ya que facilita las tareas de importar dependencias a la aplicación y el despliegue de la misma. Como he decidido realizar una API, esta forma era la más sencilla para mi.
   
**2. Se ha utilizado una base de datos en memoria (h2).**
  
    - Los datos por defecto serán cargados mediante un CommandLineRunner para que se carguen al inicio de la aplicación.

**3. La arquitectura empleada es la siguiente:**
  
    -Modelo: Contendrá los objetos que se usarán en la aplicación, en este caso Anuncios. También se ha añadido un DAO para los anuncios de los usuarios, ya que contendran menos información y habrá que mapearlos.
      
    -Repositorio: Se ha utilizado JPA repository para la persistencia, la cual se llevará a cabo en H2. Se inicializarán por defecto una serie de valores requeridos para que cuando la aplicacion inicie contenga estos datos.
      
    -Servicio: Contendrá la lógica de la aplicación, que en este caso será puntuar los anuncios y devolver los anuncios correspondientes a cada usuario.
      
    -Controlador: Se encargará de proveer los endpoints.
      
**4. La aplicación contará con los 3 Endpoints solicitados que deberán ejecutarse de la siguiente forma.**
  
    1. Ejecutar el endpoint de Puntuación para puntuar a los pisos.
      
    2. Ejecutar el endpoint de calidad o bien de usuario.
      
        **En el caso de ejecutar primero el endpoint de calidad, todos los pisos por defecto tendrán la puntuación de 0, ya que la puntuación se calcula en el endpoint de puntuación**
     

## Instrucciones de ejecución.

1. Importar proyecto como proyecto de maven en cualquier IDE de java. (Preferiblemente STS o Eclipse).
2. Es necesario tener Lombok instalado ya que se ha utilizado para getters, setters y constructores. En caso de no tenerlo descargar aquí: https://projectlombok.org/download ejecutar el JAR y poner la carpeta del IDE a utilizar.
3. Realizar un mvn clean install si el proyecto tiene errores. 
4. Ejecutar la clase PisosExampleApplication mediante "Run as Spring Boot App". 
5. La aplicación se ejecutará en el puerto 8093 por defecto.
6. Importar la colección de POSTMAN "Anuncios.postman_collection" la cual se incluye en la raiz del proyecto. En caso de que no se quiera usar este software a continuación se desarrollarán los endpoints y funciones.

## Instrucciones de utilización.

La API contará con las siguientes funciones.
*Nota* Posibles valores de mapType = 3x3 5x5 7x7 o 9x9.

|Funcion| Ruta | Tipo | Descripción |
|---|---|---|---|
| Calidad | `http://localhost:8093/quality`| `GET` |La función devolverá los pisos asociados al departamente de calidad. Podrá ver los anuncios irrelevantes|
| Puntuacion | `http://localhost:8093/puntuation`| `POST` |La función calculará los puntos de los pisos y los devolverá en el Body para poder visualizarlos.|
| Usuario | `http://localhost:8093/user`| `GET` |La función devolverá los pisos asociados a los usuarios. Podrán ver los anuncios con puntuación superior a 40 y ordenados de mejor a peor|



