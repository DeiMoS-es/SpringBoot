# Proyecto de Ejemplo

## Información del Proyecto

- **Group**: `com.corporativoX`
- **Artifact**: `nombre-del-proyecto`
- **Name**: `Nombre Descriptivo`
- **Description**: `Descripción del proyecto`
- **Package name**: `com.corporativoX.nombreDelProyecto`

## Packaging

En Spring Boot, la elección entre un archivo JAR y un archivo WAR está relacionada con la forma en que se empaqueta y despliega tu aplicación. La elección depende del tipo de aplicación que estás desarrollando y cómo planeas implementarla.

- **JAR**: Formato de archivo que contiene código Java y ya incluye un servidor de aplicaciones interno. Es adecuado para aplicaciones autónomas que no requieren un servidor de aplicaciones externo. Ideal para aplicaciones autocontenidas[^1^][1].
- **WAR**: Formato utilizado para empaquetar aplicaciones web Java que se despliegan en servidores de aplicaciones como Tomcat, Jetty o WildFly. Elige este formato cuando quieras desplegar la aplicación en un servidor de aplicaciones externo. Para aplicaciones con Spring Boot, generalmente es mejor usar JAR[^2^][2].

## Versión de Java

- **Java 17**

## Dependencias

- Lista de dependencias necesarias para el proyecto:
  - Spring Web.

## Empezando

- Para esta introducción trabajaremos con una arquitectura en capas, donde cada capa tiene una responsabilidad específica.
  - La primera capa será la capa de controladores, que se encargará de recibir las peticiones HTTP y delegar el trabajo a la capa de servicios.
### Controladores
- En las peticiones, podemos crear la llamada a GET con distintas url
- En el controlador GreetingRestController, estamos utilizando la anotación @Pathvariable para recibir un parámetro en la url y dentro del método podremos hacer uso del mismo.

### Casos prácticos
- Desarrollar una aplicación utilizando Spring Boot que permia a los usuarios verificar si una palabra ingresada es un palíndromo o no.
  - Un palíndromo es una palabra que se lee igual de izquierda a derecha que de derecha a izquierda. Ej: "radar", "reconocer".
- Requerimientos:
  - La aplicación debe ser una aplicación web que utilice Spring Boot.
  - Se debe crear un nuevo proyecto desde Spring initializer.
  - La lógica para verifica si una palabra es un palíndromo debe estar encapsulada en un método separado.
  - El endpoint debe devolver un mensaje claro, indicando si la palabra es un palíndromo o no.
  - Utiliza buenas prácticas de codificación y nombra adecuadamente tus cases y métodos.
  - Opcionalmente, documenta tu código adecuadamente utilizando comentarios cuando sea necesario.
[^1^][1]: [Baeldung - Differences Between JAR and WAR Packaging](https://www.baeldung.com/java-jar-war-packaging)
[^2^][2]: [Baeldung - Running a Spring Boot App with Maven vs an Executable Jar](https://www.baeldung.com/spring-boot-run-maven-vs-executable-jar)
