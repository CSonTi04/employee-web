package employees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.net.URI;

@ControllerAdvice
public class EmployeeErrorHandler {

  @ExceptionHandler
  public Mono<ProblemDetail> handleException(IllegalArgumentException ex) {
      return Mono.just(ex)
              .map(this::toProblemDetail);
  }

    private ProblemDetail toProblemDetail(IllegalArgumentException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Invalid Employee Data");
        problemDetail.setType(URI.create("api/employees/bad-request"));
        return problemDetail;
    }
}
