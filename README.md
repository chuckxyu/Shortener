# URL Shortener

## Overview

The URL Shortener is a web application designed to convert long URLs into shorter, more manageable links. This project provides a simple yet efficient way to create and redirect shortened URLs. It's built using Spring Boot, Java, PostgreSQL and a bit of Docker, making it easy to deploy and scale.

## Features

- **URL Shortening**: Convert long URLs into short, user-friendly links.
- **Short url expanding**: enter a short url to retrieve it's original one


## Getting Started

Follow these steps to set up and run the URL Shortener on your local machine.

### Prerequisites

Before you begin, ensure you have the following software installed:

- Java Development Kit (JDK)
- Gradle
- Docker

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/chuckxyu/Shortener.git
   ```

2. Run a docker instance with postgreSQL:

   ```bash
   docker pull postgres:latest
   docker run -d --name my-shortenerDB -e POSTGRES_PASSWORD=shortener123 -p 5432:5432 postgres:latest
   ```

3. Build and run the backend Spring Boot application:

   ```bash
   cd Shortener
   gradle clean build
   gradle bootRun
   ```

4. Use the REST endpoints to shorten or retrieve original Urls, theres a Postman collection with the live and local demo.


## Usage

-
- Shorten or expand URLs through the API.



## Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot) for the backend framework.
- [PostgreSQL](https://www.postgresql.org/) for the database.
- [Docker](https://www.docker.com/) for the database easy deployment

## Contact

If you have any questions or feedback, please contact [carlosgarciaordonez@gmail.com].

```