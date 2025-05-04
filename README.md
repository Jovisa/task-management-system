# Task Management System

Mini project on a line with task management systems like Jira or Trello,
system that allows users to create, update, and manage tasks.

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA (Hibernate)
- H2 (in-memory database for development)
- Gradle (build tool)
- Swagger / OpenAPI 3 (API docs)


## ⚙️ Getting Started

1. **Clone the repo**
   ```bash
   git clone https://github.com/your-username/task-management-system.git
   cd task-management-system
2. **Run the app**
```
   ./gradlew bootRun

```

## API Documentation

Once the application is running, you can access the Swagger UI in your browser at:

- [/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- [/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

> ℹ️ `POST /api/accounts` — Public endpoint for user registration  
> 🔐 All other endpoints require a valid JWT token

## 🔐 Authentication

This app uses **JWT-based authentication**:

1. Register via `POST /api/accounts`
2. Login via `POST /api/auth/token` using **Basic Auth**
```json
{
  "email": "user@example.com",
  "password": "securePassword123"
}
```

3. Use the received token in the `Authorization: Bearer <token>` header



