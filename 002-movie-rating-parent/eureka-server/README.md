# Configurando Eureka-Server
- En microservicios, se puede trabajar con archivos de configuración application.properties, pero lo más recomendable es trabajar con archivos de configuración en formato YAML.
- Refactorizamos el properties a YAML.
- En el archivo `application.yml` del proyecto `eureka-server`:
```yaml
server:
  port: 8761

spring:
  application:
    name: msvc-eureka
  config:
    import: optional:configserver:http://localhost:8888

eureka:
    instance:
        hostname: localhost
    client:
        register-with-eureka: false
        fetch-registry: false
        service-url:
          defaultZone: http://localhost:${server.port}/eureka/
```
- Decoramos nuestra clase principal con `@EnableEurekaServer`
