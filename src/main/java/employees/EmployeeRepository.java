package employees;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class EmployeeRepository {

    private final List<Employee> employees = List.of(
            createEmployee(1L, "John"),
            createEmployee(2L, "Jane"),
            createEmployee(3L, "Alice"),
            createEmployee(4L, "Bob")
    );

    private Employee createEmployee(Long id, String name) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        return employee;
    }

    public Flux<Employee> findAll() {
        return Flux.fromIterable(employees);
    }

    public Mono<Employee> findById(long id) {
        return Flux.fromIterable(employees)
                .filter(e -> e.getId().equals(id))
                .singleOrEmpty();
    }
}
