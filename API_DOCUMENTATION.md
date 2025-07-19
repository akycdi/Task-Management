# Task Management API Documentation

## Base URL
`/api/tasks`

---

## Endpoints

### 1. Create Task
- **POST** `/api/tasks`
- **Request Body:**
```json
{
  "title": "Task Title",
  "description": "Task Description",
  "status": "PENDING",
  "priority": "HIGH",
  "dueDate": "2025-07-31"
}
```
- **Response:**
```json
{
  "id": 1,
  "title": "Task Title",
  "description": "Task Description",
  "status": "PENDING",
  "priority": "HIGH",
  "dueDate": "2025-07-31"
}
```
- **Sample curl:**
```
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Task Title","description":"Task Description","status":"PENDING","priority":"HIGH","dueDate":"2025-07-31"}'
```

---

### 2. Get Task by ID
- **GET** `/api/tasks/{id}`
- **Response:**
```json
{
  "id": 1,
  "title": "Task Title",
  "description": "Task Description",
  "status": "PENDING",
  "priority": "HIGH",
  "dueDate": "2025-07-31"
}
```
- **Sample curl:**
```
curl -X GET http://localhost:8080/api/tasks/1
```

---

### 3. Get All Tasks
- **GET** `/api/tasks`
- **Response:**
```json
[
  {
    "id": 1,
    "title": "Task Title",
    "description": "Task Description",
    "status": "PENDING",
    "priority": "HIGH",
    "dueDate": "2025-07-31"
  },
  {
    "id": 2,
    "title": "Another Task",
    "description": "Another Description",
    "status": "COMPLETED",
    "priority": "LOW",
    "dueDate": "2025-08-01"
  }
]
```
- **Sample curl:**
```
curl -X GET http://localhost:8080/api/tasks
```

---

### 4. Update Task
- **PUT** `/api/tasks/{id}`
- **Request Body:**
```json
{
  "title": "Updated Title",
  "description": "Updated Description",
  "status": "COMPLETED",
  "priority": "MEDIUM",
  "dueDate": "2025-08-15"
}
```
- **Response:**
```json
{
  "id": 1,
  "title": "Updated Title",
  "description": "Updated Description",
  "status": "COMPLETED",
  "priority": "MEDIUM",
  "dueDate": "2025-08-15"
}
```
- **Sample curl:**
```
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated Title","description":"Updated Description","status":"COMPLETED","priority":"MEDIUM","dueDate":"2025-08-15"}'
```

---

### 5. Delete Task
- **DELETE** `/api/tasks/{id}`
- **Response:** `204 No Content`
- **Sample curl:**
```
curl -X DELETE http://localhost:8080/api/tasks/1
```

---

## Postman Collection
A sample Postman collection is provided in `postman_collection.json` in the project root.

---

## Status/Enums
- **status:** `PENDING`, `IN_PROGRESS`, `COMPLETED`
- **priority:** `LOW`, `MEDIUM`, `HIGH`

---

For more details, see the source code and tests in the repository.
