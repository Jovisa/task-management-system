# Task Management System

A mini backend project inspired by popular task management platforms like **Jira** and **Trello**.
Users can create, assign, and manage tasks.
In addition, the application provides features such as comment threads, task filtering, and secure authentication using JWT.

---

## Key Features

- 🆕 **User Registration and Authentication**  
  Users can register and securely authenticate using **JWT tokens** after logging in.


- 🧾 **Task Creation and Management**  
  Authenticated users can create tasks and view all existing tasks.


- 🎯 **Filtering**  
  Tasks can be filtered by **author** and **assignee** for easier navigation and management.


- 👥 **Task Assignment**  
  Task creators can assign their tasks to other users.


- 🔄 **Status Updates**  
  Both the **author** and the **assignee** of a task can update its status (e.g., CREATED, IN_PROGRESS, COMPLETED).


- 💬 **Commenting System**  
  Logged-in users can post comments on any task and retrieve all task-related comments.   
  Comments include author information and are sorted from newest to oldest.

---

**Example Tasks JSON**
```json
[
   {
     "id": "123",
     "title": "Fix login bug",
     "description": "Resolve the issue with OAuth2 redirect",
     "status": "CREATED",
     "author": "john.doe@example.com",
     "assignee": "none",
     "total_comments": 3
   },
   {
      "id": "1",
      "title": "new task",
      "description": "a task for anyone",
      "status": "COMPLETED",
      "author": "manager1@mail.com",
      "assignee": "developer2@mail.com",
      "total_comments": 1
   }
]
```
---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Gradle (build tool)
- H2 (in-memory database for development)

## ✅ Requirements

- **Java 17** or later  
  Ensure Java is installed and available in your system's `PATH`.

- **No need to install Gradle manually**  
  This project includes the Gradle Wrapper (`./gradlew` / `gradlew.bat`), so you can build and run the application without having Gradle preinstalled.


## ⚙️ Getting Started

1. **Clone the repo**
   ```bash
   git clone https://github.com/Jovisa/task-management-system.git
   cd task-management-system

   ```

2. **Run the app**
   ```bash
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
```json
   {
     "email": "user@example.com",
     "password": "securePassword123"
   }
```
2. **Login** via `POST /api/auth/token` using **Basic Auth** (your email and password as credentials).


3. Use the received token in the `Authorization: Bearer <token>` header



