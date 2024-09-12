# Proyecto de Microservicios de Calificación de Películas

Este proyecto es una aplicación web basada en microservicios en la que los usuarios pueden realizar operaciones CRUD sobre películas, comentarios y puntuaciones.

## Tecnologías Utilizadas
- **Lenguaje**: Java
- **Framework**: Spring Boot
- **Base de Datos**: MySQL
- **Gestión de Dependencias**: Maven
- **Servicio de Descubrimiento**: Eureka

## Estructura del Proyecto
La aplicación consta de 3 microservicios:
- **Movies**: Operaciones CRUD sobre películas.
- **Comments**: Operaciones CRUD sobre comentarios.
- **Ratings**: Operaciones CRUD sobre puntuaciones.

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

## Ejecución
Para ejecutar los microservicios, navegar a cada uno de los directorios de los microservicios y ejecutar:
```sh
mvn spring-boot:run