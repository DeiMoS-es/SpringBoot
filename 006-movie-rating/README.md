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
