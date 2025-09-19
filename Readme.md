
# Proyecto Microservicios - Client y Account

Este proyecto contiene dos microservicios (`client-service` y `account-service`) que comparten la misma base de datos MySQL. Se incluye un script SQL para inicializar la base de datos con datos de prueba y un `docker-compose.yml` para levantar todo el entorno.

---

## 🗂 Estructura del proyecto

```
├── client-service
│   └── src
├── account-service
│   └── src
├── infra
│   └── BaseDatos.sql       # Script para crear base de datos y datos de prueba
└── docker-compose.yml
```
---

## ⚙️ Configuración de conexión

Ambos microservicios utilizan un `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://mysql:3306/bank
    username: test
    password: pass123
  jpa:
    hibernate:
      ddl-auto: none # No generar schema automáticamente
    show-sql: true
```

> Nota: `mysql` es el nombre del servicio en `docker-compose.yml`.

---

## 🐳 Docker Compose - Diagrama de contenedores

```
+-----------------+           +------------------+           +------------------+
|                 |           |                  |           |                  |
|  client-service | 8081 ---> |      mysql       | 3306 ---> |  account-service | 8082
|                 |           |                  |           |                  | 
+-----------------+           +------------------+           +------------------+                 
                                       
```

- `client-service` → puerto host `8081`  
- `account-service` → puerto host `8082`  
- `mysql` → puerto host `3306`  

El contenedor MySQL ejecutará automáticamente `infra/create-db.sql`.

---

## 🚀 Levantar el proyecto

### 1️⃣ Compilar los microservicios

```bash
cd client-service
mvn clean install

cd ../account-service
mvn clean install
```

Genera los JARs en `target/`.

### 2️⃣ Construir las imágenes Docker

```bash
docker-compose build
```

### 3️⃣ Levantar los contenedores

```bash
docker-compose up -d
```

### 4️⃣ Verificar contenedores

```bash
docker ps
```

---

## Probar el servicio

Una vez levantados los contenedores, puedes probar los microservicios de las siguientes formas:

### 1. Swagger UI

Cada microservicio expone su documentación de API usando Swagger UI:

- **Account Microservice:** [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- **Client Microservice:** [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

Desde allí puedes explorar los endpoints, enviar peticiones y ver las respuestas directamente desde el navegador.

### 2. Postman

Dentro de la carpeta `infra` del proyecto encontrarás una colección de Postman (`BankProject.postman_collection.json`) que incluye todos los endpoints de ambos microservicios.

Para probar con Postman:

1. Abrir Postman.
2. Importar la colección desde `infra/Bank Microservices API.postman_collection.json`.
3. Configurar la URL base según corresponda (`http://localhost:8081` o `http://localhost:8082`).
4. Ejecutar las peticiones de prueba incluidas (crear cliente, crear cuenta, crear transacción, obtener historial, etc.).

Esto te permite probar todas las funcionalidades sin necesidad de crear las solicitudes manualmente.


## 🛑 Parar los contenedores

```bash
docker-compose down
```

### Limpiar imágenes y volúmenes

```bash
docker-compose down --rmi all -v
```

---

## 💡 Tips

- Mapear puertos si 8081 o 8082 están ocupados.  
- Modificar script SQL: eliminar volúmenes para re-inicializar MySQL.

```bash
docker-compose down -v
docker-compose up -d
```

- Ver logs:

```bash
docker logs -f client-service
docker logs -f account-service
docker logs -f mysql
```

---

## 🐋 Dockerfiles recomendados

**Client-service Dockerfile**

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/client-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
```

**Account-service Dockerfile**

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/account-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]
```

---

## docker-compose.yml de ejemplo

```yaml
version: "3.9"
services:
  mysql:
    image: mysql:8.0
    container_name: mysql
    environment:
      MYSQL_ROOT_PASSWORD: root123
      MYSQL_USER: devsu
      MYSQL_PASSWORD: devsu123
    ports:
      - "3306:3306"
    volumes:
      - ./infra/init.sql:/docker-entrypoint-initdb.d/init.sql

  client-service:
    build: ./client-service
    ports:
      - "8081:8081"
    depends_on:
      - mysql

  account-service:
    build: ./account-service
    ports:
      - "8082:8082"
    depends_on:
      - mysql
```

Con esto, tu entorno de desarrollo queda completamente levantado con Docker y los microservicios conectados a MySQL con datos iniciales.
