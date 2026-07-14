# 🤖 AI SQL Assistant

An AI-powered Full Stack web application that converts natural language into SQL queries using a Large Language Model (LLM), executes the generated SQL on a PostgreSQL database, and displays the results in a modern React interface.

---

## 📌 Overview

Writing SQL queries requires knowledge of database syntax, which many business users, analysts, and beginners may not have.

AI SQL Assistant bridges this gap by allowing users to interact with a database using plain English.

Example:

**User Input**

```
Show all employees earning more than 50000
```

↓

**AI Generated SQL**

```sql
SELECT * FROM employees WHERE salary > 50000;
```

↓

The query is executed on PostgreSQL and the results are displayed instantly.

---

# ✨ Features

- 🤖 Convert English to SQL using AI (Groq Llama 3.3)
- 🗄️ Execute generated SQL on PostgreSQL
- 📊 Display query results in a dynamic table
- 📋 Copy generated SQL with one click
- 🌙 Dark / Light mode
- 🔔 Toast notifications
- ⌨️ Ctrl + Enter shortcut
- 🧹 Clear workspace
- ⚡ Loading indicator
- 🔒 Secure execution by allowing only SELECT queries

---

# 🏗️ Architecture

```
                User
                  │
                  ▼
           React Frontend
                  │
                  ▼
        Spring Boot REST API
                  │
                  ▼
          Groq AI (LLM)
                  │
     Natural Language → SQL
                  │
                  ▼
        SQL Validation Layer
                  │
                  ▼
      PostgreSQL Database
                  │
                  ▼
         Query Results (JSON)
                  │
                  ▼
          React Data Table
```

---

# 🛠 Tech Stack

## Frontend

- React (Vite)
- Tailwind CSS
- Axios
- React Toastify

## Backend

- Java 21
- Spring Boot
- Spring JDBC
- REST APIs
- Maven

## Database

- PostgreSQL

## AI

- Groq API
- Llama 3.3 70B Versatile

## Tools

- Git
- GitHub
- Postman
- VS Code
- IntelliJ IDEA

---

# 📷 Screenshots

> Add screenshots after deployment.

Suggested screenshots:

- Home Screen
- SQL Generation
- Query Results
- Dark Mode
- Copy SQL Notification

---

# 🚀 Getting Started

## Clone Repository

```bash
git clone <repository-url>
```

## Backend

```bash
cd backend
./mvnw spring-boot:run
```

## Frontend

```bash
cd frontend
npm install
npm run dev
```

---

# 📡 API Endpoint

### Generate SQL & Execute Query

**POST**

```
/api/sql/generate
```

Request

```json
{
  "prompt": "Show all employees"
}
```

Response

```json
{
  "sql": "SELECT * FROM employees;",
  "data": [
    {
      "id": 1,
      "name": "John Smith",
      "department": "IT",
      "salary": 65000
    }
  ]
}
```

---

# 🔒 Security

To prevent accidental or malicious database modifications, the backend only allows **SELECT** statements to be executed.

Queries such as:

- DELETE
- UPDATE
- DROP
- ALTER

are rejected before reaching the database.

---

# 💡 Real-World Use Cases

- Business Intelligence Dashboards
- HR Analytics
- Finance Reporting
- Customer Support Systems
- Inventory Management
- Sales Analytics
- Internal Data Exploration Tools

---

# 🔮 Future Improvements

- Automatically discover database schema (tables and columns) using JDBC `DatabaseMetaData` and provide it to the AI for schema-aware SQL generation.
- Support multiple database systems such as MySQL, SQL Server, and Oracle.
- Query history and favorites.
- Export results to CSV/Excel.
- User authentication and role-based database access.
- SQL syntax highlighting and downloadable SQL scripts.
- Support AI-generated JOIN queries across multiple related tables.

---

# 👨‍💻 Author

**Farhan Mahmood**

Java Full Stack Developer | AI Enthusiast

---

# ⭐ If you like this project

Give it a ⭐ on GitHub!