# AgriConnect System Backend

**Mid-Term Project Examination – INSY 8322 Web Technology and Internet**  
**Adventist University of Central Africa (AUCA)**

---

## Student Details
* **Student Name:** ISHIMWE David
* **Student ID:** 26426
* **Project Name:** AgriConnect System

---

## Project Description
AgriConnect links smallholder farmers with the agronomists who advise them. A farmer requests a farm visit that is conducted by an agronomist; the visit moves from `REQUESTED` to `PLANNED` and ends `VISITED` or `CALLED_OFF`. Each agronomist is attached to one cooperative and specialises in several crops such as maize or coffee. After a visit has taken place, the agronomist records recommendations with the practice to apply and the follow-up period.

Built with:
* **Java 27**
* **Spring Boot 4.1.2** (Spring Web, Spring Data JPA)
* **PostgreSQL Database**
* **Hibernate ORM**

---

## Prerequisites
1. **Java JDK 27** installed and configured (`JAVA_HOME`).
2. **PostgreSQL** running locally on port `5432`.
3. **Postman** for API testing.

---

## Steps to Run the API

### 1. Create the Database
In PostgreSQL, create a database named `agri_connect_db`:
```sql
CREATE DATABASE agri_connect_db;
```

### 2. Configure Database Credentials
Open `src/main/resources/application.properties` and verify your credentials:
```properties
spring.application.name=agri-connect-system

spring.datasource.url=jdbc:postgresql://localhost:5432/agri_connect_db
spring.datasource.username=postgres
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 3. Build and Run the Application
In your project root directory, run:
```bash
./mvnw clean spring-boot:run
```
Or run `AgriConnectSystemApplication.java` directly in your IDE (IntelliJ IDEA).

Tomcat will start on:
```text
http://localhost:8080
```

---

## Testing with Postman
Import the provided collection file at the root of this repository:
```text
agri-connect-system-midterm.postman_collection.json
```
The collection includes every requirement request labeled by ID (`A2`, `B1`, `B2`, `B3`, `C1`, `C2`, `C3`, `C4`, `D1`, `D2`, `D3`, `D4`, `E1`, `E2`, `E3`, `F1`, `F2`, `Bonus Dashboard`, `Bonus Pagination`).
