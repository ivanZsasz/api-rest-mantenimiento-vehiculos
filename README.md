# API REST de Mantenimiento de Vehículos

Esta aplicación proporciona una API RESTful para la gestión y seguimiento del mantenimiento de vehículos. Desarrollada como un proyecto backend estructurado, permite el registro de automóviles y la asignación de sus respectivos servicios de mantenimiento a través de relaciones de base de datos.

## Tecnologías Utilizadas
- **Java 17:** Lenguaje principal de desarrollo.
- **Spring Boot 3:** Framework base para la construcción de la API.
- **Spring Data JPA:** Acceso a datos mediante mapeo objeto-relacional (ORM).
- **H2 Database:** Base de datos relacional en memoria (facilita el despliegue).
- **Jakarta Validation:** Motor de validación de reglas de negocio en la capa de entrada.

## Arquitectura
El proyecto sigue una estricta **Arquitectura en Capas (MVC)**:
- **Controllers:** Exponen los endpoints HTTP.
- **Services:** Concentran la lógica de negocio.
- **Repositories:** Gestionan la persistencia en base de datos.
- **Patrón DTO:** Desacopla el modelo interno de la base de datos de los datos expuestos hacia el exterior.

## Instalación y Ejecución Local
Al utilizar una base de datos en memoria (H2) y estar gestionado por Maven, no se requiere configuración externa de bases de datos.

1. Clona el repositorio:
   `git clone https://github.com/ivanZsasz/api-rest-mantenimiento-vehiculos.git`
2. Posiciónate en la carpeta del proyecto.
3. Ejecuta la aplicación usando el wrapper de Maven:
   `./mvnw spring-boot:run`
4. La API estará disponible en `http://localhost:8081`.
