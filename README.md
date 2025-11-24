# Employees WebFlux - Reactive Spring Boot Application

A reactive Spring Boot application demonstrating Spring WebFlux and reactive programming patterns using Project Reactor.

## 🚀 Features

- **Reactive REST API** built with Spring WebFlux
- **Non-blocking I/O** using Reactor types (`Mono` and `Flux`)
- **In-memory repository** for employee management
- **Service layer** with DTO mapping
- **Lombok** integration for reduced boilerplate code
- **Record classes** for immutable DTOs

## 🏗️ Architecture

This project follows a layered architecture pattern:

- **Controller Layer**: RESTful endpoints using reactive types
- **Service Layer**: Business logic and DTO transformation
- **Repository Layer**: Data access with reactive streams
- **Domain Model**: Entity and DTO classes

### Key Components

#### Employee Model
```java
@Data
public class Employee {
    private Long id;
    private String name;
}
```

#### Employee DTO
```java
public record EmployeeDto(Long id, String name) {
}
```

#### Reactive Repository
The `EmployeeRepository` provides reactive data access:
- `findAll()`: Returns `Flux<Employee>` for streaming multiple employees
- `findById(long id)`: Returns `Mono<Employee>` for single employee lookup

#### Service Layer
The `EmployeeService` handles business logic:
- Transforms entities to DTOs using reactive operators
- Maps `Flux<Employee>` to `Flux<EmployeeDto>`

#### Hello World Endpoint
A simple reactive endpoint demonstrating `Mono` usage:
- **GET** `/hello` - Returns a greeting message

## 🛠️ Technologies

- **Spring Boot** - Application framework
- **Spring WebFlux** - Reactive web framework
- **Project Reactor** - Reactive streams implementation
- **Lombok** - Code generation for getters/setters/constructors
- **Java Records** - Immutable data carriers

## 📋 Prerequisites

- Java 17 or higher
- Maven or Gradle

## 🚦 Getting Started

### Running the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Testing the API

Using the provided HTTP file:

```http
GET http://localhost:8080/hello
```

Expected response:
```json
{
  "message": "Hello World"
}
```

## 📚 Reactive Programming Concepts

### Mono vs Flux

- **Mono<T>**: Represents 0 or 1 element
  - Used for single value responses
  - Example: `findById()` returns `Mono<Employee>`

- **Flux<T>**: Represents 0 to N elements
  - Used for streaming multiple values
  - Example: `findAll()` returns `Flux<Employee>`

### Reactive Operators

The project demonstrates key reactive operators:
- **map()**: Transform elements in the stream (e.g., Entity → DTO)
- **filter()**: Filter elements based on conditions
- **singleOrEmpty()**: Convert Flux to Mono expecting single result

### Benefits of Reactive Approach

- ✅ **Non-blocking**: Better resource utilization
- ✅ **Backpressure**: Handle data flow efficiently
- ✅ **Scalability**: Handle more concurrent requests
- ✅ **Composability**: Chain operations declaratively

## 🔜 Future Enhancements

- [ ] Add CRUD endpoints for Employee management
- [ ] Implement reactive database integration (R2DBC)
- [ ] Add error handling and validation
- [ ] Implement WebClient for external API calls
- [ ] Add integration tests with WebTestClient
- [ ] Implement Server-Sent Events (SSE)
- [ ] Add employee controller to expose service methods

## 📖 Learning Resources

- [Spring WebFlux Documentation](https://docs.spring.io/spring-framework/reference/web/webflux.html)
- [Project Reactor Reference Guide](https://projectreactor.io/docs/core/release/reference/)
- [Reactive Streams Specification](https://www.reactive-streams.org/)

## 📝 License

This is a training project for learning reactive programming with Spring WebFlux.