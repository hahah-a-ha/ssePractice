//package org.example.sse;
//
//import org.springframework.http.MediaType;
//import org.springframework.http.codec.ServerSentEvent;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;
//
//import java.time.Duration;
//import java.time.LocalTime;
//
//@RestController
//public class sController {
//
//
//
//    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<ServerSentEvent<String>> sse() {
//        return Flux.interval(Duration.ofSeconds(1))
//                .map(sequence -> ServerSentEvent.builder("Notification: " + LocalTime.now()).build());
//    }
//
//
//}
