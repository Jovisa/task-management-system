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
## 🧪 API Overview
> POST /api/accounts — Register new user (public)

> POST /api/auth/token — Get JWT token (requires basic auth)

> POST /api/tasks — Create a task (authenticated)

> PUT /api/tasks/{id}/assign — Assign task to user (author only)

> PUT /api/tasks/{id}/status — Change task status (author or assignee)

> GET /api/tasks?author=email&assignee=email — Filter tasks

> POST /api/tasks/{id}/comments — Add comment

> GET /api/tasks/{id}/comments — Get task comments

> ℹ️ `POST /api/accounts` — Public endpoint for user registration  
> 🔐 All other endpoints require a valid JWT token

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



