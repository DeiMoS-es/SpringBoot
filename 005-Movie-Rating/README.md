# App seguimiento de películas

## Contenidos
- [Descripción](#descripción)
- [Tecnologías](#tecnologías)
- [Instrucciones](#instrucciones)
- [Arquitectura](#arquitectura)
- [Contribuir](#contribuir)

## Descripción
La aplicación permite:
- Llevar un seguimiento de las películas que has visto, estás viendo y quieres ver.
- Agregar, editar y eliminar películas.
- Filtrar películas por estado (vista, viendo, pendiente).
- Buscar películas por nombre.
- Ver el detalle de una película.
- Marcar una película como vista o viendo.

## Tecnologías
- Spring Boot
- Java 17
- Maven
- MySQL
- Arquitectura Hexagonal
## Arquitectura

![Diagrama Hexagonal](assets/images/9eqcL7e.png)

- **Capa de Dominio**:
    - Contiene las entidades (`User`, `Movie`) y los servicios de dominio (`DomainService`).

- **Capa de Aplicación**:
    - Contiene los casos de uso (`UserService`, `MovieService`).

- **Capa de Infraestructura**:
    - Contiene los controladores (`UserController`, `MovieController`), los repositorios (`JpaUserRepository`, `JpaMovieRepository`) y las implementaciones de los servicios.

## Instrucciones
1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/usuario/repo.git
   cd repo
