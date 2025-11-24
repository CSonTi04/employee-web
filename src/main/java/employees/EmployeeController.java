package employees;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public Flux<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<EmployeeDto>> findById(@PathVariable Long id) {
        return employeeService
                .findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    //Created-nél szükséges a helyes URI visszaadása
    @PostMapping
    public Mono<ResponseEntity<EmployeeDto>> createEmployee(
            @RequestBody Mono<EmployeeDto> employeeDto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return employeeService
                .save(employeeDto)
                .map(e -> ResponseEntity.created(uriComponentsBuilder.path("api/employees/{id}").buildAndExpand(e.id()).toUri()).body(e));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<EmployeeDto>> createEmployee(@PathVariable Long id, @RequestBody Mono<EmployeeDto> employeeDto) {
        return employeeDto
                .filter(e -> e.id() != null && e.id().equals(id))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Employee with id " + id + " not found")))
                .flatMap(
                        e -> employeeService.save(employeeDto)
                )
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteById(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
