# Proyecto de Microservicios de Calificación de Películas

Este proyecto es una aplicación web basada en microservicios en la que los usuarios pueden realizar operaciones CRUD sobre películas, comentarios y puntuaciones.

## Tecnologías Utilizadas
- **Lenguaje**: Java
- **Framework**: Spring Boot
- **Base de Datos**: MySQL
- **Gestión de Dependencias**: Maven
- **Servicio de Descubrimiento**: Eureka

## Estructura del Proyecto
La aplicación consta de 4 microservicios:
- **Movies**: Operaciones CRUD sobre películas.
- **Comments**: Operaciones CRUD sobre comentarios.
- **Ratings**: Operaciones CRUD sobre puntuaciones.
- **Users**: Operaciones CRUD sobre usuarios.

El proyecto es modular, con un proyecto "padre" que contiene los microservicios o módulos.

## Primeros Pasos
1. Eliminar la carpeta `src` del proyecto "padre".
2. En el `pom.xml` del proyecto "padre", eliminar el contenido de las etiquetas `<dependencies>`, `<build>` y dejar solo la etiqueta `<modules>` con los nombres de los microservicios.

## Configuración del Proyecto Padre
1. En la etiqueta `<build>` del `pom.xml` del proyecto "padre", añadir la siguiente configuración:
    ```xml
    <pluginManagement>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>3.3.3</version>
            </plugin>
        </plugins>
    </pluginManagement>
    ```
2. En la etiqueta `<dependencies>` del `pom.xml` del proyecto "padre" (para que las hereden los proyectos "hijos"), añadir la siguiente dependencia:
    ```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.22</version>
    </dependency>
    ```
3. Debajo de la etiqueta `<name>`, para indicar a Spring que este no es un proyecto ejecutable sino un contenedor de proyectos, añadir:
    ```xml
    <packaging>pom</packaging>
    ```
### Notas
- Además de los microservicios mencionados anteriormente, tendremos:
  - **Eureka**: Servicio de descubrimiento. Es un servidor de registro y descubrimiento de servicios.
  - **ApiGateway**: Puerta de enlace para los microservicios. Es como la puerta de entrada a la aplicación, recibe el request y lo redirige al microservicio correspondiente.
  - **ConfigServer**: Servidor de configuración. Es un servidor que centraliza la configuración de los microservicios.
- La iteración del proyecto sería:
  - El cliente realiza una petición al ApiGateway. 

## Creando los microservicios

### ApiGateway
1. Crear un proyecto Spring Boot con las siguientes dependencias:
    - `Eureka Discovery Client`
    - `Spring Cloud Gateway`
    - `Config Client`
    - `Spring Boot Actuator`

2. En el archivo `pom.xml`el microservicio api-gateway, añadir la configuración para indicar que es un proyecto hijo:
    ```xml
    <parent>
        <groupId>com.movie.rating</groupId>
        <artifactId>movie-rating-parent</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    ```
2.1 Y en el `pom.xml` del "padre":
```xml
<modules>
    <module>api-gateway</module>
</modules>
```
### Eureka
1. Crear un proyecto Spring Boot con las siguientes dependencias:
    - `Eureka Server`
    - `Config Client`
    - `Spring Boot Actuator`
2. Repetimos los pasos 2 y 2.1 del microservicio anterior.
### ConfigServer
1. Crear un proyecto Spring Boot con las siguientes dependencias:
    - `Config Server`
2. Repetimos los pasos 2 y 2.1 del microservicio ApiGateway.
### Users-Services
1. Crear un proyecto Spring Boot con las siguientes dependencias:
    - `Spring Web`
    - `Spring Data JPA`
    - `MySQL Driver`
    - `Eureka Discovery Client`
    - `Config Client`
    - `Spring Boot Actuator`
2. Repetimos los pasos 2 y 2.1 del microservicio ApiGateway.
## Ejecución
Para ejecutar los microservicios, navegar a cada uno de los directorios de los microservicios y ejecutar:
```sh
mvn spring-boot:run