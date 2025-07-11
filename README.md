# LensCure Backend (Spring Boot)  

**🔗 Full-Stack Project:** [Frontend (React)](https://github.com/Aounil/LensCure-Frontend)  

LensCure Backend is the server-side application for the LensCure platform — an online system for managing optical products, orders, users, and roles (clients, admins, stock managers).

## Features

- 🔐 JWT Authentication — Secure login and protected API endpoints.
- 👥 User Management — Create, update, and manage users with roles.
- 📦 Product Management — CRUD operations for optical products.
- 🛒 Order Management — Place and track orders.
- ⚙️ Role-Based Access Control — Enforce permissions by user role.
- 🌐 CORS support — Allow frontend and backend to communicate.

## Tech Stack

- Java 17+
- Spring Boot (REST API)
- Spring Security with JWT
- JPA / Hibernate
- postgressql
- Maven

## Run Locally

### Clone the project

```bash
git clone https://github.com/Aounil/LensCure-Backend.git
```

### Go to the project directory

```bash
cd LensCure-Backend
```


### Build and run

Run using Maven:

```bash
mvn spring-boot:run
```

Or build jar and run:

```bash
mvn clean package
java -jar target/lenscure-backend.jar
```

The backend server will be available at [http://localhost:8080](http://localhost:8080).

## API Reference

- `/auth/login` and `/auth/register` — Authentication endpoints
- `/admin/users` — User management (admin only)
- `/products` — Product management
- `/orders` — Order management

> All protected endpoints require a JWT token in the `Authorization` header:

```
Authorization: Bearer YOUR_JWT_TOKEN
```

## Authors

- [@Yassine Aounil](https://github.com/Aounil)

## Notes

- Ensure the frontend uses the correct backend URL.
- Adjust CORS configuration if needed.
- Keep your JWT secret secure.
