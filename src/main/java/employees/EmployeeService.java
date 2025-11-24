package employees;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Flux<EmployeeDto> findAll() {
        return employeeRepository.findAll().map(
                employee -> new EmployeeDto(employee.getId(), employee.getName())
        );
    }

    public Mono<EmployeeDto> findById(Long id) {
        return employeeRepository.findById(id)
                .map(employee -> new EmployeeDto(employee.getId(), employee.getName()));
    }
}
