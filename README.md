# Notas sobre este repositorio

## Creación de Proyectos

- Para crear todos los proyectos que existirán en este repositorio, vamos a usar la web: Spring Initializr.
  - **Nota:** Esta web genera un esqueleto de proyecto, listo para usar.

## Herramientas y Tecnologías

- Todos los proyectos se van a desarrollar con **Maven** y **Java 17**, a no ser que se indique lo contrario en el proyecto.
  - **Maven:** Herramienta de gestión y comprensión de proyectos.
  - **Java 17:** Versión de Java utilizada para el desarrollo.

## Versión de Spring Boot

- La versión de **Spring Boot** será siempre la última disponible en el momento de crear el proyecto.
  - **Spring Boot:** Framework para simplificar el desarrollo de aplicaciones basadas en Spring.

## Ejemplo de Uso

1. Accede a Spring Initializr.
2. Configura el proyecto con las dependencias necesarias.
3. Descarga el proyecto generado.
4. Importa el proyecto en tu IDE favorito.
5. ¡Comienza a desarrollar!

## Anotaciones
- Cuando trabajamos con Spring, constantemente escuchamos el término **Decorar una clase**.
- Este termino se refiere a la aplicación de anotaciones de Spring en una clase, para configurar su comportamiento y funcionalidad dentro de Spring Framework.
- Al decorar una clase, indicas a Spring que debe manejar y configurar esa clase dentro de su contenedor de inversión de control, lo que permite la administración de componentes, la inyección de dependencias, la configuración de propiedades, entre otras funcionalidades.
- Se usan para configurar y definir el comportamiento de los componentes de una aplicación.
- Ejemplo: **@GetMapping**, de manera opcional puede ir acompañado de un atributo **@GetMapping("/clientes")**.
- **@RestController** simplifica el desarrollo de APIs RESTfull al combinar la anotación @Controller y @ResponseBody, permitiendo a los dedarrolladores crear controladores que generen respuestas HTTP en formato JSON o XML de manera fácil y rápida.
- **@GetMapping**, se utiliza para mapear las solicitudes HTTP GET.
- **@PathVariable** se utiliza para mapear partes de la URL de una solicitud web a parámetros de un controlador de Spring. Indica que un parámetro Java se transformará en un parámetro web que estará contenido en la URL de un endpoint.

## Arquitectura en Capas:
- Enfoque comunmente utilizado para diseñar sistemas de software, se puede aplicar en el diseño y desarrollo de un microservicio.
- ### Capas:
    - Presentación: suele ser mínima o incluso inexistente en los microservicios ya que generalmente no tienen una interfaz de usuario directa
    - Lógica de negocio: esta capa se compone principalmente de servicios, que son las clases que implementan la lógica de negocio específica. Esto significa que los servicios son responsables de implementar reglas de negeocio, realizar cálculos, validar datos.
    - Acceso a datos: es la capa responsable de ineractuar con la base de datos u otros sistemas de almacenamiento de datos.
    - Opcionales integración y seguridad: la de **integración** se utiliza para integrar el microservicio con otros dentro de la misma arquitectura de microservicios, puede incluir componentes de comunicación como clientes HTTP, clientes de servicio web, colas de mensajes, etc. La de **seguridad** se encarga de implementar medidas de seguridad, como la autenticación y la autorización.
## MicroServicios:
- Los microservicios son una arquitectura de software en la que una aplicación monolítica se descompone en pequeños servicios independientes. Cada uno de estos servicios se centran en realizar una tarea espetífica, y pueden ser desarrollados, implementados y escalados de manera independiente.
- ### Ventajas_
  - Escalabilidad independiente.
  - Despliegue independiente.
  - Facilidad de mantenimiento.
  - Flexibilidad Tecnológica.
  - Tolerancia a fallos.
- ### Desventajas:
  - Complejidad de la Gestión.
  - Mayor complejidad de Testing.
