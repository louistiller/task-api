# Task Management API

Backend for a full-stack task management application built with Spring Boot.

The application provides user authentication, user-specific task management, and a REST API for managing tasks.

## Features

- User registration and login
- Session-based authentication
- CSRF protection
- User-specific task data
- Create, read, update, and delete tasks
- Mark tasks as completed
- Pagination and sorting
- PostgreSQL persistence
- Centralized exception handling
- OpenAPI / Swagger documentation

## Tech Stack

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
- OpenAPI / Swagger

## Project Structure

The backend follows a layered structure:

- `controller` – REST endpoints
- `service` – application logic
- `repository` – database access
- `model` – entities and request/response models
- `config` – security and OpenAPI configuration
- `exception` – exception handling

## Frontend

Frontend repository:

[Task Management Frontend](https://github.com/louistiller/task-frontend)

Live application:

https://task-frontend-rho-vert.vercel.app

## Running Locally

### Requirements

- Java
- PostgreSQL

Create a PostgreSQL database named:

```text
taskdb
```

Start the backend using the local profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

The backend will be available at:

```text
http://localhost:8080
```

## Deployment

The backend is deployed on Render and serves the production API used by the frontend.

## API Documentation

Local Swagger UI:

http://localhost:8080/swagger-ui/index.html

Online Swagger UI:

https://task-api-u38p.onrender.com/swagger-ui/index.html

## Security

The application uses Spring Security with session-based authentication.

State-changing requests are protected against CSRF attacks. Tasks are associated with authenticated users so that each user can only access their own task data.