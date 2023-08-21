# Task Management System

This is a comprehensive Task Management System API built using GraphQL and Spring Boot. The API allows users to manage
their tasks, create task lists, set due dates, and track their progress. It demonstrates my proficiency in GraphQL
schema design, Spring Boot development, database interaction, and user authentication.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [GraphQL Playground](#graphql-playground)
- [Contributing](#contributing)

## Prerequisites

- Java 8+
- Gradle (for building the project)
- Your favorite IDE (IntelliJ, Eclipse, etc.)
- Postman (for testing)

## Installation

**1. Clone Repository:**

```bash
git clone https://github.com/singh-akxhay/task-management-system.git -b main
```

**2.Configure `application.yml` file**

**3. Build Project Using Gradle:**

```bash
./gradlew build
```

**4. Run the Application:**

```bash
./gradlew bootRun
```

## Usage

**1. Import Postman Collection:**

Import the provided Postman
collection ([Task Management - GraphQL Queries.json](src/main/resources/postman_collection/Task%20Management%20-%20GraphQL%20Queries.json))
into Postman.

**2. Explore Endpoints:**

The collection contains sample requests for various GraphQL queries and mutations. Use these requests to test different
functionalities of the API.

## GraphQL Playground

The GraphQL playground allows you to interact with the API using a user-friendly interface. It's available
at http://localhost:8080/graphiql when the application is running.

## Contributing

Contributions are always welcome! If you find a bug or have suggestions for improvements, please open an issue or submit
a pull request.