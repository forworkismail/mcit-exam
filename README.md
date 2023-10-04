# MCIT Exam

This project consists of a Spring Boot backend and an Angular frontend.

## Prerequisites

1. Java (Recommended version: 11 or above)
2. Maven (or you can use the Maven Wrapper included in the project)
3. Node.js & npm

## Getting Started

### Backend (Spring Boot)

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/forworkismail/mcit-exam.git 
   cd mcit-exam 
   ```

2. **Install Maven Dependencies**:
   If you have Maven installed on your machine:
   ```bash
   mvn install
   ```
   If you don't have Maven installed, you can use the Maven Wrapper:
   For Unix-based systems:
   ```bash
   ./mvnw install
   ```
   For Windows:
   ```bash
   mvnw.cmd install
   ```

3. **Run Tests**:
   To ensure everything is set up correctly, run the tests:
   For Unix-based systems:
   ```bash
   ./mvnw test
   ```
   For Windows:
   ```bash
   mvnw.cmd test
   ```

4. **Start the Spring Boot Application**:
   For Unix-based systems:
   ```bash
   ./mvnw spring-boot:run
   ```
   For Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

### Frontend (Angular)

1. **Navigate to the Frontend Directory**:
   ```bash
   cd frontend
   ```

2. **Install Dependencies**:
   ```bash
   npm install
   ```

3. **Run the Angular Application**:
   Ensure the Spring Boot application is running before you start the Angular app.
   ```bash
   npm start
   ```

   Or, if you prefer:
   ```bash
   ng serve
   ```

4. Open your browser and navigate to `http://localhost:4200`.