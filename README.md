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


## 🧪 API Overview (Selected Endpoints)

| Method | Endpoint                                 | Description                             | Access           |
|--------|------------------------------------------|-----------------------------------------|------------------|
| POST   | `/api/accounts`                          | Register new user                       | Public           |
| POST   | `/api/auth/token`                        | Get JWT token                           | Basic Auth       |
| POST   | `/api/tasks`                             | Create a task                           | Authenticated    |
| PUT    | `/api/tasks/{id}/assign`                 | Assign task to user                     | Author only      |
| PUT    | `/api/tasks/{id}/status`                 | Change task status                      | Author/Assignee  |
| GET    | `/api/tasks?author=email&assignee=email`| Filter tasks by author and/or assignee  | Authenticated    |
| POST   | `/api/tasks/{id}/comments`               | Add comment to a task                   | Authenticated    |
| GET    | `/api/tasks/{id}/comments`               | Get comments for a task                 | Authenticated    |

➡️ Full interactive documentation (Once the application is running):
- [/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- [/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

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



