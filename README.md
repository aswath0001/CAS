# Course Allocation System

A secure Spring Boot application for managing courses and student data with bulk import capabilities.

## Features
- JWT Authentication & Authorization
- Bulk upload of courses/students via Excel & TXT files
- RESTful APIs with Swagger documentation
- CRUD operations for course/student management

## Tech Stack
- **Backend:** Spring Boot 3, Java 17
- **Security:** JWT, Spring Security
- **Database:** Spring Data JPA (H2/MySQL)
- **File Processing:** Apache POI, Commons CSV
- **API Docs:** Swagger UI/OpenAPI 3
- **Build:** Maven

## Getting Started
1. Clone repo
2. Configure `application.properties`
3. Run with `mvn spring-boot:run`
4. Access Swagger UI at `http://localhost:8080/swagger-ui.html`
