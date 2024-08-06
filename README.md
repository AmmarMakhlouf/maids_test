# Library Management System

## Overview

This project is a Spring Boot application for managing a library system. It provides APIs to manage books, patrons, and borrowing records. The project includes functionalities for adding, updating, retrieving, and deleting books and patrons, as well as tracking borrowing and returning of books.

## Features

- **Book Management:** Add, update, retrieve, and delete books.
- **Patron Management:** Add, update, retrieve, and delete patrons.
- **Borrowing Records:** Track borrowing and returning of books.
- **Logging:** Uses Aspect-Oriented Programming (AOP) to log method calls, exceptions, and performance metrics.
- **Caching:** Implements caching for frequently accessed data to improve performance.
- **Unit Testing** Unit tests for most important API endpoints functionalities.

## Most Important Technologies Used

- **Spring Boot:** For building the application.
- **Maven** as build and dependency manager.
- **Spring Data JPA:** For database interaction.
- **H2 Database:** An in-memory database for development and testing.
- **AspectJ:** For AOP logging.
- **Spring Cache:** For caching frequently accessed data.
- **Mockito** For unit tests.


## Prerequisites

- **Java 11 or later**: Ensure you have JDK 11 or a later version installed.
- **Maven or Gradle**: For building the project (Maven is used in this project).

## Getting Started

### Clone the Repository

First, clone the repository to your local machine:

```bash
git clone git@github.com:AmmarMakhlouf/maids_test.git

### For testing use the following command under working directory:
mvn test
