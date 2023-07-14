# WeatherSure Application Backend

This repository contains the backend implementation for WeatherSure, built using Spring Boot. The backend provides a set of APIs for managing weather data, serving both the weather administrators and the platform.

## Tech Used

WeatherSure backend is built using the following technologies:

- **Spring Boot**: The backend framework used to develop the application.
- **Java**: The programming language used for the backend development.
- **Redis**: A fast in-memory data store used for caching and improving performance.
- **MongoDB**: A NoSQL database used for storing and retrieving weather data.
- **Swagger**: A tool for designing, building, documenting, and consuming RESTful APIs.

## Prerequisites

Before running the Weather Application backend, ensure that you have the following dependencies installed on your machine:

- Java Development Kit (JDK): The backend is developed using Java, so you need to have JDK installed. The recommended version is JDK 8 or later.
- Redis: You need to have Redis installed and running. You can download Redis from the official website or use a package manager to install it.
- MongoDB: You need to have MongoDB installed and running. You can download MongoDB from the official website or use a package manager to install it.
- Apache Maven: Maven is used as the build tool for the project. Make sure you have Maven installed.

## Getting Started

To run the Weather Application backend on your local machine, follow these steps:

1. Clone the repository to your local machine:
   ```sh
   git clone <repository-url>

2. Navigate to the project directory:
   ```sh
   cd weatherSure-backend

3. Build the project using Maven:
   ```sh
   mvn clean install

4. Run the application using Maven:
   ```sh
   mvn spring-boot:run

5. The backend server will start running on the default port 8080 (http://localhost:8080).

## API Endpoints Snapshot
![alt text](https://github.com/kshittijagrawal/weatherSure-backEnd/blob/master/assets/Endpoints.png)

## Repository Structure
![alt text](https://github.com/kshittijagrawal/weatherSure-backEnd/blob/master/assets/Primary%20Repo.png)

![alt text](https://github.com/kshittijagrawal/weatherSure-backEnd/blob/master/assets/Current%20Data%20Repo.png)

*PS : For additional info of the data model and repository structure, head onto the [assets](https://github.com/kshittijagrawal/weatherSure-backEnd/tree/master/assets) directory in the repository.*

## Contributing
Contributions to WeatherSure Application backend are welcome! If you find any issues or would like to add new features, please open an issue or submit a pull request.

