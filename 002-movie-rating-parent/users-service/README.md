# Configurando Users-Service
- En microservicios, se puede trabajar con archivos de configuración application.properties, pero lo más recomendable es trabajar con archivos de configuración en formato YAML.
- Refactorizamos el properties a YAML.
- En el archivo `application.yml` del proyecto `users-service`:
```yaml
  port: 8090

  spring:
    application:
      name: msvc-student
    config:
      import: optional:configserver:http://localhost:8888
    datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: ${DB_URL}
      username: ${DB_USER_NAME}
      password: ${DB_PASSWORD}
    jpa:
      hibernate:
        ddl-auto: create
      database: mysql
      database-platform: org.hibernate.dialect.MySQL8Dialect

  eureka:
    instance:
      hostname: localhost
    client:
      service-url:
        defaultZone: http://localhost:8761/eureka/
```
- Decoramos la clase principal con el decorador `@EnableDiscoveryClient`.
# Estructura del microservicio
- Creamos la estructura de paquetes:
  - `com.ms.userservice.controller`
  - `com.ms.userservice.models`
  - `com.ms.userservice.models.entities`
  - `com.ms.userservice.models.dtos`
  - `com.ms.userservice.repository`
  - `com.ms.userservice.service`

# Entidades
- Creamos la entidad `User` en el paquete `com.ms.userservice.entity`:
  - Se ha añadido un atributo movieId, este atributo es importante, porque al trabajar con microservicios es como trabajar con relaciones. En este caso, el usuario tiene una relación con la película , por lo que se necesita un atributo que haga referencia a la película.
  - Para crear el atributo userId, se ha utilizado la anotación @UUIDGenerator y se ha usado el tipo de dato UUID. Los UUID son valores de 128 bits y se representan típicamente como cadenas. En Java, puedes usar la clase UUID para generar y manejar UUIDs.