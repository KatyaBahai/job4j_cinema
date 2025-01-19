## Mindful Cinema

Mindful Cinema is a cinema website that provides a seamless experience for users to explore a list of films, register on the website, view available film sessions, and purchase tickets.

### Features

- Browse a list of films.

- User registration and authentication.

- View available film sessions.

- "Purchase" tickets through the website.

### Installation Instructions

Follow these steps to run the project locally:

1) Clone the repository:
```
git clone <repository-url>
```
2) Create a local database:

3) Name the database cinema.

4) Configure the database connection:

- Open the db/liquibase.properties file.

- Specify your database login and password:
```
url=jdbc:postgresql://localhost:5432/cinema
username=<your-username>
password=<your-password>
```
- Run Liquibase:

- Pre-create and autofill tables by executing Liquibase migrations.

5) Launch the application:

You can start the application using one of the following methods:

- Using the Main class:

Navigate to src/main/java/ru/job4j/cinema and run the Main class.

- Via Maven:

Compile and run the project with:
```
mvn spring-boot:run
```
6) Access the application:

Open your browser and go to: http://localhost:8080/index

### Technologies Used

Java 17

Spring Boot

Sql2O 1.6.0

Thymeleaf 3.0.4.

H2database 2.1.214

Bootstrap

Liquibase 4.15.0

PostgreSQL 42.5.1

### Contact

You can reach out via Telegram: @Midori_Sun

   
