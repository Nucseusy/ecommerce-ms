
# Ecommerce MS

## Descripción
Trata de un microservicio diseñado para gestionar la consulta de precios de productos en una tienda de comercio electrónico. Permite a los usuarios consultar precios según la fecha, el producto y la marca, ofreciendo siempre el precio más adecuado según las reglas de negocio establecidas. La solución propuesta ha sido cuidadosamente diseñada e implementada priorizando la claridad y la eficiencia del desarrollo, asegurando que se aborden de manera precisa y efectiva los requisitos específicos delineados en la descripción del test. Se ha puesto especial énfasis en la simplicidad y la legibilidad del código, facilitando así tanto la comprensión como la extensión futura del proyecto.

## Arquitectura
El desarrollo se fundamenta en la Arquitectura Hexagonal, asegurando una clara separación entre la lógica de negocio y los mecanismos de entrada/salida, lo que facilita la mantenibilidad, la escalabilidad y la posibilidad de realizar pruebas independientes de infraestructura externa.

## Tecnologías Utilizadas
- Java 17
- Spring Boot 3
- Spring Data JPA
- Lombok
- JUnit 5 para pruebas unitarias
- Base de datos H2 en memoria
- MapStruct para el mapeo de objetos
- Jackson para la serialización/deserialización JSON
- OpenApi

## Configuración
El proyecto está configurado para levantar de forma sencilla y directa. Al ejecutarse, automáticamente se inicializa en el puerto 8080, creando las tablas necesarias en una base de datos H2 en memoria y cargando los datos esenciales para su funcionamiento inmediato, sin requerir pasos adicionales de configuración.

## Documentación
Para ver a detalle la documentación de la API generada por Swagger, utilizar el siguiente link: http://localhost:8080/swagger-ui/index.html#/

## Tests
### Usando Postman
Para facilitar la validación del endpoint de consulta, se incluye el siguiente comando curl, diseñado para simular una solicitud de consulta de precios

```
curl --location 'http://localhost:8080/product/price?productId=35455&brandId=1&applicationDate=2020-06-15%2010%3A00%3A00'
```
### Tests de integración
Se encuentran en la clase de test ProductControllerTest.java


