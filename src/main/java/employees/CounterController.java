package employees;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;

@RestController
@RequestMapping("api/counter")
public class CounterController {

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Long>> getCounter() {
        return Flux.range(0, 10)
                .map(i -> ServerSentEvent.<Long>builder()
                        .id(UUID.randomUUID().toString())
                        .event("counter-event")
                        .data((long) i)
                        .comment("Counter at " + i)
                        .build())
                .delayElements(java.time.Duration.ofSeconds(1));
    }
}
