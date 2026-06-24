# Blog Application Backend

GitHub Repository: https://github.com/MeghaChauhan278/BlogApplicationBackend

A beginner-friendly Spring Boot backend for a simple blog application. This project provides REST APIs to manage categories, posts, and comments using Spring Boot, Spring Data JPA, and MySQL.

## Project Overview

This backend allows you to:

- Create, view, update, and delete blog categories
- Create, view, update, and delete blog posts
- Create, view, update, and delete comments
- Connect many posts to one category
- Connect many comments to one post
- Validate required request fields using Jakarta Validation

The project currently focuses only on backend CRUD APIs. Authentication, pagination, search, security, and advanced features are not added yet.

## Features

- Category CRUD APIs
- Post CRUD APIs
- Comment CRUD APIs
- MySQL Database Integration
- DTO Pattern Implementation
- Layered Architecture
- Entity Relationships using JPA
- Request Validation using Jakarta Validation
- API Testing with Postman
  
## Technologies Used

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Jakarta Validation
- MySQL
- Lombok
- Maven

## Project Structure

src/main/java/com/megha/blogapplicationbackend

├── controller
│   ├── CategoryController
│   ├── PostController
│   └── CommentController
│
├── dto
│   ├── CategoryDTO
│   ├── PostDTO
│   └── CommentDTO
│
├── entity
│   ├── Category
│   ├── Post
│   └── Comment
│
├── repository
│   ├── CategoryRepository
│   ├── PostRepository
│   └── CommentRepository
│
├── service
│   ├── CategoryService
│   ├── PostService
│   └── CommentService
│
└── service/impl
    ├── CategoryServiceImpl
    ├── PostServiceImpl
    └── CommentServiceImpl

## Architecture Diagram

```text
Client / Postman
       |
       v
Controller Layer
Handles HTTP requests and responses
       |
       v
Service Layer
Contains business logic
       |
       v
Repository Layer
Communicates with the database using Spring Data JPA
       |
       v
MySQL Database
Stores categories, posts, and comments
```

## Database Schema

```text
categories
----------
id            BIGINT PRIMARY KEY AUTO_INCREMENT
title         VARCHAR
description   VARCHAR

posts
-----
id            BIGINT PRIMARY KEY AUTO_INCREMENT
title         VARCHAR
content       VARCHAR
category_id   BIGINT FOREIGN KEY REFERENCES categories(id)

comments
--------
id            BIGINT PRIMARY KEY AUTO_INCREMENT
name          VARCHAR
email         VARCHAR
content       VARCHAR
post_id       BIGINT FOREIGN KEY REFERENCES posts(id)
```

## Entity Relationships

```text
Category 1 -------- * Post
One category can have many posts.
Many posts belong to one category.

Post 1 ------------ * Comment
One post can have many comments.
Many comments belong to one post.
```

In the code:

- `Post` uses `@ManyToOne` with `Category`
- `Comment` uses `@ManyToOne` with `Post`

## API Endpoints

### Category APIs

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/categories` | Create a category |
| GET | `/categories` | Get all categories |
| GET | `/categories/{id}` | Get one category by ID |
| PUT | `/categories/{id}` | Update a category |
| DELETE | `/categories/{id}` | Delete a category |

### Post APIs

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/posts` | Create a post |
| GET | `/posts` | Get all posts |
| GET | `/posts/{id}` | Get one post by ID |
| PUT | `/posts/{id}` | Update a post |
| DELETE | `/posts/{id}` | Delete a post |

### Comment APIs

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/comments` | Create a comment |
| GET | `/comments` | Get all comments |
| GET | `/comments/{id}` | Get one comment by ID |
| PUT | `/comments/{id}` | Update a comment |
| DELETE | `/comments/{id}` | Delete a comment |

## How to Run the Project

### 1. Create MySQL Database

Open MySQL and create a database:

```sql
CREATE DATABASE blogdb;
```

### 2. Update Database Credentials

Open `src/main/resources/application.properties` and make sure your MySQL username and password are correct.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogdb
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run the Application

Use Maven:

```bash
mvn spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

## Sample Requests and Responses

### Create Category

Request:

```http
POST /categories
Content-Type: application/json
```

```json
{
  "title": "Java",
  "description": "Posts related to Java programming"
}
```

Response:

```json
{
  "id": 1,
  "title": "Java",
  "description": "Posts related to Java programming"
}
```

### Get All Categories

Request:

```http
GET /categories
```

Response:

```json
[
  {
    "id": 1,
    "title": "Java",
    "description": "Posts related to Java programming"
  }
]
```

### Create Post

Request:

```http
POST /posts
Content-Type: application/json
```

```json
{
  "title": "Introduction to Spring Boot",
  "content": "Spring Boot makes it easy to create Java backend applications.",
  "categoryId": 1
}
```

Response:

```json
{
  "id": 1,
  "title": "Introduction to Spring Boot",
  "content": "Spring Boot makes it easy to create Java backend applications.",
  "categoryId": 1
}
```

### Get One Post

Request:

```http
GET /posts/1
```

Response:

```json
{
  "id": 1,
  "title": "Introduction to Spring Boot",
  "content": "Spring Boot makes it easy to create Java backend applications.",
  "categoryId": 1
}
```

### Create Comment

Request:

```http
POST /comments
Content-Type: application/json
```

```json
{
  "name": "Megha",
  "email": "megha@example.com",
  "content": "This post is helpful.",
  "postId": 1
}
```

Response:

```json
{
  "id": 1,
  "name": "Megha",
  "email": "megha@example.com",
  "content": "This post is helpful.",
  "postId": 1
}
```

### Update Comment

Request:

```http
PUT /comments/1
Content-Type: application/json
```

```json
{
  "name": "Megha",
  "email": "megha@example.com",
  "content": "This updated comment is helpful.",
  "postId": 1
}
```

Response:

```json
{
  "id": 1,
  "name": "Megha",
  "email": "megha@example.com",
  "content": "This updated comment is helpful.",
  "postId": 1
}
```

### Delete Category, Post, or Comment

Request examples:

```http
DELETE /categories/1
DELETE /posts/1
DELETE /comments/1
```

Successful response:

```text
204 No Content
```

## Validation Rules

The project uses Jakarta Validation to reject blank required fields.

Category:

- `title` cannot be blank
- `description` cannot be blank

Post:

- `title` cannot be blank
- `content` cannot be blank

Comment:

- `name` cannot be blank
- `email` cannot be blank
- `content` cannot be blank

If a required field is blank, Spring Boot returns a validation error response.

## Future Improvements

- Spring Security Authentication
- JWT Based Authorization
- Pagination and Sorting
- Search Functionality
- Swagger API Documentation
- Docker Containerization
