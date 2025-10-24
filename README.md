# Microservicio de Cuentas

Este proyecto es un **microservicio Java Spring Boot** que gestiona la información de cuentas y movimientos de cuentas bancarias de clientes.
Forma parte del sistema de ejemplo.
---
##Tecnologías Utilizadas

- **Java 11**
- **Spring Boot 2.7.14**
- **Spring Data JPA**
- **Oracle Database (ojdbc8 driver)**
- **Swagger OpenAPI (springdoc)**
- **JasperReports 6.20.5** para generacion de reportes PDF
- **Maven 3.8.9** para la gestion de dependencias

##Estructura del proyecto

microservicioCuentas/
|
|--src/
| |--main/java/com/devsu/test/microservicionCuentas/
| | |--controller/ -> controladores REST
| | |--entity/ -> Entidades JPA (Cuenta, Movimiento)
| | |--repository/ -> Interfaces JPA
| | |--service/ -> Logica de negocio
| | |--dto/ -> Clases de transferencia de datos (DTO)
| |--main/resources/
| | |--application.properites--> configuracion del proyecto
| | |--reportes/--> archivos de los reportes .jasper y .jrxml
| ----pom.xml--> configuracion de Maven



