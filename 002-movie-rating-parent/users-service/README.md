# Configurando Users-Service
- En microservicios, se puede trabajar con archivos de configuración application.properties, pero lo más recomendable es trabajar con archivos de configuración en formato YAML.
- Refactorizamos el properties a YAML.
- En el archivo `application.yml` del proyecto `users-service`:
```yaml
server:
  port: 8090

spring:
  application:
    name: msvc-student
  datasource:
    driver-class-name: com.mysql.cj.jdb.Driver
    url: jdbc:mysql://localhost:3336/movie_rating_users
    username: mrusers
    password: mrusers
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