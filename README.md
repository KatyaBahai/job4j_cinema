## Mindful Cinema

Mindful Cinema is a cinema website that provides a list of films, register on the website, view available film sessions, and purchase tickets.

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

Jacoco 0.8.7

### Contact

You can reach out via Telegram: @Midori_Sun

![Screenshot_1](https://github.com/user-attachments/assets/8516532c-4f02-4d3b-a9c8-41fc2ffc3fc4)

![Screenshot_9](https://github.com/user-attachments/assets/220201ca-00c4-47df-bed5-82fa46f0b459)

![Screenshot_13](https://github.com/user-attachments/assets/da8bae35-144d-4b52-8de2-77845397efec)

![Screenshot_14](https://github.com/user-attachments/assets/58732877-b6d3-4cdb-8121-a961c8bc1b71)

![Screenshot_10](https://github.com/user-attachments/assets/714e6b42-463f-414b-aa86-2002b89ff1b2)

![Screenshot_12](https://github.com/user-attachments/assets/5880765a-4170-43ba-b58d-95f8efea4f17)

![Screenshot_15](https://github.com/user-attachments/assets/6c9a36b1-0563-451f-b6c7-b8ff52aa9e93)

![Screenshot_2](https://github.com/user-attachments/assets/3976eec4-087b-440b-8f5e-43dc2e5de225)

![Screenshot_11](https://github.com/user-attachments/assets/4fd2323c-bceb-4be6-aaad-ed940a09cf29)




