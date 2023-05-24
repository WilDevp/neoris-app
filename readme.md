## Especificación Técnica del Proyecto

### Introducción

Especificación técnica para el desarrollo de una API REST utilizando Spring Boot y una base de datos relacional. La aplicación tendrá un modelo de datos compuesto por las entidades Persona, Cliente, Cuenta y Movimiento. Todos estos componentes se desplegarán en un contenedor Docker.

### Arquitectura de la Aplicación

La aplicación seguirá la arquitectura de microservicios y hexagonal y se basará en Spring Boot, un framework que facilita el desarrollo de aplicaciones basadas en Spring y Java.

La API REST expondrá puntos finales para cada entidad, permitiendo operaciones CRUD (Crear, Leer, Actualizar, Borrar) sobre ellas. Cada punto final corresponderá a una entidad y tendrá una URL asociada.

### Tecnologías a utilizar

-   Java: Lenguaje de programación principal para el desarrollo del backend.
-   Spring Boot: Framework para facilitar el desarrollo de aplicaciones Java.
-   Spring Data JPA: Para el acceso y manipulación de la base de datos.
-   JUnit: Framework para realizar pruebas unitarias.
-   Docker: Para contenerizar la aplicación.
-   Base de datos relacional: (MySQL, PostgreSQL, etc.) para el almacenamiento persistente de datos.

### Entidades

#### Persona

Una clase abstracta con las siguientes propiedades:

-   Nombre
-   Género
-   Edad
-   Identificación: Clave primaria (PK).
-   Dirección
-   Teléfono

#### Cliente

Una entidad que extiende Persona con las siguientes propiedades adicionales:

-   ClienteId: Clave primaria (PK).
-   Contraseña
-   Estado

#### Cuenta

Una entidad con las siguientes propiedades:

-   NúmeroCuenta: Clave primaria (PK).
-   TipoCuenta
-   SaldoInicial
-   Estado

#### Movimiento

Una entidad con las siguientes propiedades:

-   IdMovimiento: Clave primaria (PK).
-   Fecha
-   TipoMovimiento
-   Valor
-   Saldo

### Endpoints

-   `/cuentas`: para realizar operaciones CRUD sobre la entidad Cuenta.
-   `/clientes`: para realizar operaciones CRUD sobre la entidad Cliente.
-   `/movimientos`: para realizar operaciones CRUD sobre la entidad Movimiento.
-   `/reportes`: para obtener un informe del estado de la cuenta de un cliente en un rango de fechas determinado.

### Flujos de trabajo principales

-   Creación, actualización, lectura y eliminación de entidades (Cliente, Cuenta, Movimiento).
-   Manejo de transacciones: los créditos se almacenan como positivos, los débitos como negativos. Se almacena el saldo disponible después de cada transacción. Si el saldo es cero y se realiza una transacción de débito, se muestra el mensaje "Saldo no disponible".
-   Generación de informes: dado un rango de fechas y un cliente, mostrar las cuentas asociadas con sus saldos respectivos y el total de débitos y créditos realizados durante esas fechas para ese cliente.


**Pasos para ejecutar el contenedor de Docker:**

 -  Asegúrate de tener Docker y Docker Compose instalados en tu sistema.
 - Descarga la imagen de docker desde docker hub: `docker pull wildevp/neoris-app`
 -  Abre una terminal o línea de comandos y navega hasta el directorio donde se encuentran los archivos `docker-compose.yml` y `Dockerfile`.
    
 -  Ejecuta el comando `docker-compose up` para construir la imagen y levantar los servicios.
    
 -  Accede en `http://localhost:8080` desde el navegador.
 
 -  Recuerda modificar el archivo `.env-example` con tus datos.
    
 -  Cuando hayas terminado, utiliza el comando `docker-compose down` para detener y eliminar los contenedores.
    

Recuerda reemplazar los valores en el archivo `docker-compose.yml` con tus propias configuraciones, como contraseñas y nombres de base de datos.
