Description
The Web Library project is an online platform designed to manage a library system. It provides functionalities for both students and administrators.

Functionalities for Students:
Search for Books: Students can search for books by ID.
Get All Books: Students can get a list of all books available in the library.
Get Available Books: Students can get a list of available books that can be borrowed.
Get Missing Books: Students can get a list of missing books that are not available.
Get Received Books: Students can get a list of books they have received.
Reserve Books: Students can reserve books for future borrowing.
Rate Books: Students can rate specific books.
Functionalities for Administrators:
Register Books: Admins can register new books in the library.
Track Book Availability: Admins can track which books are available and which are not.
Edit Books: Admins can edit book details such as title, author, etc.
Delete Books: Admins can remove books from the library.
Register Students: Admins can register students into the system.
Edit Students: Admins can edit student details such as name, class, etc.
Deactivate Students: Admins can deactivate student accounts.
Move Students: Admins can move students from one class to another.
Get Student by ID: Admins can retrieve student information by their ID.
Project Structure
/src: Contains the source code for the project.
/controllers: Contains controller classes for handling HTTP requests.
/entites: Contains entity classes for representing data in the database.
/repositories: Contains repository classes for interacting with the database.
/services: Contains service classes for implementing business logic.
/resources: Contains configuration files, such as application properties.
/test: Contains test cases for testing the functionality of the application.
Getting Started
To run the Web Library project locally, follow these steps:

Clone the repository to your local machine.
Set up the database configuration in application.properties.
Build and run the project using your preferred IDE or the command line.
Access the project using a web browser at http://localhost:8080.
Log in as an administrator or student and start using the functionalities provided.
Technologies Used
Java
Spring Boot
Spring MVC
Spring Data JPA
MySQL
