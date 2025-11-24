# Employees WebFlux - Reactive Spring Boot Application

A reactive Spring Boot application demonstrating Spring WebFlux and reactive programming patterns using Project Reactor.

## 🚀 Features

- **Reactive REST API** built with Spring WebFlux
- **Full CRUD operations** for employee management
- **Non-blocking I/O** using Reactor types (`Mono` and `Flux`)
- **In-memory repository** with atomic ID generation
- **Service layer** with DTO mapping
- **Global error handling** with RFC 7807 Problem Details
- **Server-Sent Events (SSE)** for real-time data streaming
- **Lombok** integration for reduced boilerplate code
- **Record classes** for immutable DTOs

## 🏗️ Architecture

This project follows a layered architecture pattern:

- **Controller Layer**: RESTful endpoints using reactive types
- **Service Layer**: Business logic and DTO transformation
- **Repository Layer**: Data access with reactive streams
- **Error Handler**: Centralized exception handling
- **Domain Model**: Entity and DTO classes

### Key Components

#### Employee Model
```java
@Data
@AllArgsConstructor
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
The `EmployeeRepository` provides reactive CRUD operations:
- `findAll()`: Returns `Flux<Employee>` for streaming all employees
- `findById(long id)`: Returns `Mono<Employee>` for single employee lookup
- `save(Employee employee)`: Creates or updates an employee
- `deleteById(long id)`: Deletes an employee by ID

#### Service Layer
The `EmployeeService` handles business logic:
- Transforms entities to DTOs using reactive operators
- Maps `Flux<Employee>` to `Flux<EmployeeDto>`
- Uses `flatMap()` for nested reactive operations

#### Error Handling
Global exception handler using `@ControllerAdvice`:
- Handles `IllegalArgumentException` for business logic errors
- Handles `WebExchangeBindException` for validation errors
- Returns RFC 7807 Problem Details for standardized error responses

#### Server-Sent Events
The `CounterController` demonstrates real-time streaming:
- Streams counter values from 0 to 10
- Uses `ServerSentEvent` for structured event data
- Implements 1-second delay between events

## 🛠️ Technologies

- **Spring Boot** - Application framework
- **Spring WebFlux** - Reactive web framework
- **Project Reactor** - Reactive streams implementation
- **Lombok** - Code generation for getters/setters/constructors
- **Java Records** - Immutable data carriers
- **Problem Details (RFC 7807)** - Standardized error responses

## 📋 Prerequisites

- Java 17 or higher
- Maven or Gradle

## 🚦 Getting Started

### Running the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### API Endpoints

#### Hello World
```http
GET http://localhost:8080/hello
```

#### Get All Employees
```http
GET http://localhost:8080/api/employees
```

#### Get Employee by ID
```http
GET http://localhost:8080/api/employees/1
```

#### Create Employee
```http
POST http://localhost:8080/api/employees
Content-Type: application/json

{
  "name": "John Doe"
}
```

#### Update Employee
```http
POST http://localhost:8080/api/employees/1
Content-Type: application/json

{
  "id": 1,
  "name": "John Doe Updated"
}
```

#### Delete Employee
```http
DELETE http://localhost:8080/api/employees/1
```

#### Counter SSE Stream
```http
GET http://localhost:8080/api/counter
```

## 📚 Reactive Programming Concepts

### Mono vs Flux

- **Mono<T>**: Represents 0 or 1 element
  - Used for single value responses
  - Example: `findById()` returns `Mono<Employee>`
  - `save()` method accepts `Mono<EmployeeDto>` for non-blocking operations

- **Flux<T>**: Represents 0 to N elements
  - Used for streaming multiple values
  - Example: `findAll()` returns `Flux<Employee>`
  - `range()` creates a sequence of numbers for SSE

### Reactive Operators

The project demonstrates key reactive operators:
- **map()**: Transform elements in the stream (e.g., Entity → DTO)
- **flatMap()**: Transform and flatten nested reactive types
- **filter()**: Filter elements based on conditions
- **singleOrEmpty()**: Convert Flux to Mono expecting single result
- **doOnNext()**: Perform side effects (note: should be avoided when possible)
- **delayElements()**: Add time delays between elements

### Important Patterns

#### Avoiding Side Effects
```java
// ⚠️ Side effects in reactive streams (avoid this)
.doOnNext(existingEmployee -> existingEmployee.setName(employee.getName()))
```
Similar to `peek()` in Java Streams, `doOnNext()` should be used cautiously as it introduces side effects.

#### Non-blocking Service Methods
```java
public Mono<EmployeeDto> save(Mono<EmployeeDto> employeeDto) {
    return employeeDto
        .map(dto -> new Employee(dto.id(), dto.name()))
        .flatMap(employeeRepository::save)
        .map(this::toDto);
}
```
Accepting `Mono<T>` instead of `T` ensures truly non-blocking operations.

### Benefits of Reactive Approach

- ✅ **Non-blocking**: Better resource utilization
- ✅ **Backpressure**: Handle data flow efficiently
- ✅ **Scalability**: Handle more concurrent requests
- ✅ **Composability**: Chain operations declaratively
- ✅ **Real-time streaming**: Built-in SSE support

## 🎯 Implemented Features

- ✅ Full CRUD operations for employees
- ✅ Reactive repository with atomic ID generation
- ✅ Service layer with DTO transformation
- ✅ Global error handling with Problem Details
- ✅ Server-Sent Events demonstration
- ✅ HTTP test file for easy API testing

## 🔜 Future Enhancements

- [ ] Implement reactive database integration (R2DBC)
- [ ] Add bean validation annotations
- [ ] Implement WebClient for external API calls
- [ ] Add integration tests with WebTestClient
- [ ] Add WebSocket support
- [ ] Implement pagination for employee listing
- [ ] Add filtering and sorting capabilities

## 📖 Learning Resources

- [Spring WebFlux Documentation](https://docs.spring.io/spring-framework/reference/web/webflux.html)
- [Project Reactor Reference Guide](https://projectreactor.io/docs/core/release/reference/)
- [Reactive Streams Specification](https://www.reactive-streams.org/)
- [RFC 7807 - Problem Details](https://www.rfc-editor.org/rfc/rfc7807)

## 📝 License

This is a training project for learning reactive programming with Spring WebFlux.