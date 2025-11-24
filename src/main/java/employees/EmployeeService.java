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
                this::toDto
        );
    }

    public Mono<EmployeeDto> findById(Long id) {
        return employeeRepository.findById(id)
                .map(this::toDto);
    }

    //Azért mono, hogy valóban ne legyen blokkoló a hívás
    public  Mono<EmployeeDto> save(Mono<EmployeeDto> employeeDto) {
        return employeeDto
                .map(dto -> new Employee(dto.id(), dto.name()))
                .flatMap(employeeRepository::save)//Micsoda mandíner gecc
                .map(this::toDto);
    }

    public Mono<Void> deleteById(Long id) {
        return employeeRepository.deleteById(id);
    }

    private EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getName());
    }
}
