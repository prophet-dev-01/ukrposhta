# ✉️Ukrposhta🟨🟦

This project is a RESTful web service for managing an IT companies internal operations.
It allows for the management of teams consisting of managers, developers, QA specialists,
and DevOps engineers of varying levels of experience. All team members can work on multiple
projects simultaneously, and the system uses a relational database to store data.


## 🎥Application presentation
[Click here to watch the presentation](https://drive.google.com/file/d/1p8pk0JQ6qX7LASg_oQIKYsxDUy-vOrvI/view?usp=share_link)

## ✨Features
 - The API supports CRUD operations for managing employees and projects, with the following endpoints:

### 🔑Authentication
- POST /register - creates a new user account
### 🔆Tasks
- POST /tasks - creates a new task
- GET /tasks - returns all tasks
- GET /tasks/{id} - returns a single task by ID
- PUT /tasks/{id} - updates a task by ID
- DELETE /tasks/{id} - deletes a task by ID
### 👨🏽‍💻Employees
- PUT /employees/{id}/role?roleName={roleName}: adds a role to an employee
- GET /employees: retrieves all employees
- GET /employees/by-email?email={email}: retrieves an employee by their email
- PUT /employees/{id}: updates an employee by their ID
- DELETE /employees/{id}/role?roleName={roleName}: deletes a role from an employee by their ID
- DELETE /employees/{id}: deletes an employee by their ID
### 🌐Teams
- POST /teams - creates a new team
- GET /teams - returns all teams
- GET /teams/{id} - returns a single team by ID
- POST /teams/{id} - updates a team by ID
- DELETE /teams/{id} - deletes a team by ID
- PATCH /teams/{id}/employee?employeeId={employeeId} - adds an employee to a team
- PATCH /teams/{id}/project?projectId={projectId} - adds a project to a team
### 🧩Projects
- POST /projects: creates a new project
- GET /projects/{id}: retrieves a specific project by its ID
- GET /projects/{id}/teams: retrieves all teams associated with a project by its ID
- PUT /projects/{id}: updates a specific project by its ID
- PATCH /projects/{id}/status?status={status}: updates the status of a project by its ID
- DELETE /projects/{id}: deletes a specific project by its ID
### 👀Add
Additionally, the system has an @ExceptionHandler for handling exceptions.

## 🔨Technologies
- ☕Java
- 🔒Spring Security
- Spring Boot JPA, MVC
- Hibernate
- PostgreSQL
- MapStruct
- Mockito
- Pagination
- Swagger UI

## ⚡️Getting Started
1. Clone the repository
2. Install dependencies:
```
cd ukrposhta
mvn install
```
3. Set up the PostgreSQL database and edit the configuration in "application.properties" file
``` java
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
4. Run the server:
```
mvn spring-boot:run
```
5. Open the Swagger UI in your browser:
``` link
http://localhost:8080/swagger-ui/#/
```
