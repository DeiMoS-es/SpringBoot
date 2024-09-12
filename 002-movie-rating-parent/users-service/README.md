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