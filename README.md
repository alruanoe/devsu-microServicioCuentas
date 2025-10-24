# Microservicio de Cuentas

Este proyecto es un **microservicio Java Spring Boot** que gestiona la información de cuentas y movimientos de cuentas bancarias de clientes.
Forma parte del sistema de ejemplo.
---
##Autora
Ana Luisa Ruano Espina
annaruanoespina@gmail.com
Ingeniera en Sistemas - Guatemala

##Tecnologías Utilizadas

- **Java 11**
- **Spring Boot 2.7.14**
- **Spring Data JPA**
- **Oracle Database (ojdbc8 driver)**
- **Swagger OpenAPI (springdoc)**
- **JasperReports 6.20.5** para generacion de reportes PDF
- **Maven 3.8.9** para la gestion de dependencias

##Estructura del proyecto

microServicioCuentas/
│
├── src/
│ ├── main/java/com/devsu/test/microservicioCuentas/
│ │ ├── controller/ → Controladores REST
│ │ ├── entity/ → Entidades JPA (Cuenta, Movimiento)
│ │ ├── repository/ → Interfaces JPA
│ │ ├── service/ → Lógica de negocio
│ │ └── dto/ → Clases de transferencia de datos (DTO)
│ └── main/resources/
│ ├── application.properties → Configuración de conexión a Oracle
│ ├── reportes/ → Archivos .jasper y .jrxml
│ 
│
└── pom.xml → Configuración de Maven


## Configuración del entorno

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/TU_USUARIO/microServicioCuentas.git
   cd microServicioCuentas

2.Configurar la conexión a Oracle en el archivo application.properties:

3.Ejecutar pruebas con
```bash
mvn test

4.Compilar y ejecutar el proyecto
```bash
mvn clean install
mvn spring-boot:run



5.Levantar Swagger
##Documentación Swagger

Una vez ejecutado el microservicio, puedes acceder a la documentación interactiva en:

 http://localhost:8082/swagger-ui.html

