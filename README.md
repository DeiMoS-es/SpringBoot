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
- ### Ventajas
  - Escalabilidad independiente.
  - Despliegue independiente.
  - Facilidad de mantenimiento.
  - Flexibilidad Tecnológica.
  - Tolerancia a fallos.
- ### Desventajas:
  - Complejidad de la Gestión.
  - Mayor complejidad de Testing.

# ¿Qué es una API?
- Es un conjunto de definiciones y protocolos que facilitan la comunicación y la interacción entre diferentes componentes de software. Las cuales actúan como puentes que permiten aplicaicones y sistemas se comuniquen de manera eficiente y coherente.
- Significa Interfaz de Programación de Aplicaciones.
- Sirven como intermediarios esenciales entre sistemas y aplicaciones.
- La arquitectura de una API se refiere a las reglas que rigen cómo se comparten los datos con los clientes.
- Arquitecturas:
    - SOAP.
    - RPC.
    - WebSocket.
    - REST. Es la arquitectura más común para APIs debido a su escalabilidad y facilidad de implementación. Está basada en estándares web que utilizan métodos HTTP para realizar oprecaciones CRUD.
# ¿Qué es una API rest?
- Es un tipo específico de API que sigue los principios del diseño REST. REST es un estilo de arquitectura para diseñar servicios web que se basa en un conjunto de restricciones y principios, como:
    - Separación cliente-servidor.
    - Interfaz uniforme, todas las peticiones y respuestas pertenecen a un identificador uniforme de recursos (URI), se transmiten a través de protocolos HTTP.
    - Sin estado, las aplicaciones de servidor no pueden almacenar datos de las peticiones de los clientes.
    - Sistema en capas.
    - Almacenamiento en caché.
## Diferencias:
- Estilo de Arquitectura: Una API puede seguir cualquier estilo de arquitectura, mientras que una API REST sigue específicamente los principios del diseño REST.
- Protocolo: Las APIs REST generalmente utilizan HTTP/HTTPS como protocolo de comunicación, aunque no están limitadas a ello.
- Interfaz Uniforme: Las APIs REST utilizan una interfaz uniforme y métodos HTTP estándar, mientras que otras APIs pueden utilizar diferentes enfoques y protocolos.

# ¿Qué es el enrutamiento?
 - Es el proceso mediante el cual Spring Boot decide qué método de controlador ejecutar en respuesta a una solicitud HTTP específica.
 - Esto se consifue gracias a las anotaciones:
   - @GetMapping.
   - @PostMapping.
   - @PutMapping.
   - @DeleteMapping.
# Clase ResponseEntity:
- La clase ResponseEntity se utiliza para representar toda la respuesta HTTP, incluyendo el cuerpo, los encabezados y el estado. De esta manera podremos controlar de manera detallada cómo se construyen y devuelven las respuestas HTTP desde los controladores.
- Es considerada una clase "Wrapper" (envoltorio), ya que encapsula un objeto dentro de la misma.
## Códigos de repsuesta HTTP:
- Los códigos de respuesta http son códigos numéricos estándar que los servidores envían a los clientes, para indicar el resultado de una solicitud HTTP.
- Tipos:
  - **200** OK: La solicitud ha tenido éxito.
  - **201** Created: La solicitud ha tenido éxito y se ha creado un nuevo recurso como resultado.
  - **204** No Content: La solicitud ha tenido éxito pero no hay contenido que devolver.
  - **400** Bad Request: El servidor no puede procesar la solicitud debido a un error del cliente.
  - **401** Unauthorized: La solicitud requiere autenticación.
  - **403** Forbidden: El servidor entiende la solicitud pero se niega a autorizarla.
  - **404** Not Found: El servidor no puede encontrar el recurso solicitado.
  - **405** Method Not Allowed: El método de solicitud no está permitido para el recurso solicitado.
  - **409** Conflict: La solicitud no se puede completar debido a un conflicto con el estado actual del recurso.
  - **422** Unprocessable Entity: Entidad no procesable. Se utiliza para errores de validación.
  - **500** Internal Server Error: El servidor encontró una condición inesperada que le impidió completar la solicitud.
  - **502** Bad Gateway: El servidor, actuando como puerta de enlace o proxy, recibió una respuesta inválida del servidor upstream.
  - **503** Service Unavailable: El servidor no está disponible para manejar la solicitud debido a un mantenimiento o sobrecarga temporal.
  - **504** Gateway Timeout: El servidor, actuando como puerta de enlace o proxy, no recibió una respuesta a tiempo del servidor upstream.


