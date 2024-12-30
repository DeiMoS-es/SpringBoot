# Movie Rating Application

## Descripción

Una aplicación monolítica que permite a los usuarios registrarse, crear listas de películas para ver, marcar películas como vistas y en un futuro, puntuarlas. La aplicación también integra la API de The Movie Database (TMDb) para obtener datos sobre películas populares.

## Características

- Registro y autenticación de usuarios
- CRUD de usuarios
- CRUD de películas
- Crear y gestionar listas de películas
- Integración con la API de TMDb para obtener datos de películas

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- WebClient
- H2 Database (para desarrollo y pruebas)
- Maven

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/tu-usuario/movie-rating-application.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd movie-rating-application
    ```
3. Instala las dependencias:
    ```sh
    mvn install
    ```
4. Ejecuta la aplicación:
    ```sh
    mvn spring-boot:run
    ```

## Uso
1. Accede a la aplicación en `http://localhost:8080`.
2. Regístrate y crea una cuenta.
3. Crea y gestiona tus listas de películas.
4. Consulta las películas populares y añade las que te interesen a tus listas.

## Desarrollo
- Crear el proyecto con Spring Initializr
- Se utilizará programación reactiva con Spring WebFlux y WebClient.
- Configurar la base de datos MySQL:
  - Crear la configuración de la base de datos MySQL
  - Añadimos la configuración del properties
- Empezamos desarrollando las llamadas a la API de TMDb
  - Creamos las entidades y los repositorios
  - Creamos los servicios
  - Creamos los controladores
- Creamos las entidades y los repositorios de la aplicación (movie)
  - Configuramos para devolver las películas de la base de datos paginadas
  - Se crea el método para actualizar la bbdd de movies, obteniendo los datos de la bbdd de TMDb
## Rutas
- Api Tmdb
  - **GET** api/tmdb: Realiza una petición a la API de TMDb y devuelve las películas de la primera página por defecto y las almacena en la base de datos. Si se le pasa el parámetro page=2 por ejemplo, devolverá las películas de la página 2.
  - **GET** api/tmdb/pageable: Devuelve las películas de la API de TMDb paginadas-
- Movies
  -  **GET** /movies: Devuelve todas las películas de la base de datos paginadas.
  - **POST** /movies: Añade una película a la base de datos.
  - **POST** /movies/updateDB: Almacena las películas de la bbdd de TMDb en la bbdd de la aplicación dándole el formato personalizado.
  - **DELETE** /movies/id: Borra una película de la base de datos.
## Contribuir

Las contribuciones son bienvenidas. Para contribuir:

1. Haz un fork del repositorio.
2. Crea una rama para tu función (`git checkout -b feature/nueva-funcionalidad`).
3. Haz commit de tus cambios (`git commit -am 'Añadir nueva funcionalidad'`).
4. Empuja tu rama (`git push origin feature/nueva-funcionalidad`).
5. Crea un nuevo Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

## Contacto

Tu Nombre - [tu-email@dominio.com](mailto:tu-email@dominio.com)

Repositorio del proyecto: [https://github.com/tu-usuario/movie-rating-application](https://github.com/tu-usuario/movie-rating-application)
