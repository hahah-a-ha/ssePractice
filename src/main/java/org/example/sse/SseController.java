package org.example.sse;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://3.36.130.233:8080")
public class SseController {
    private final SseEmitterService sseEmitterService;

    //응답 mime type 은 반드시 text/event-stream 이여야 한다.
    //클라이언트로 부터 SSE subscription 을 수락한다.
    //@GetMapping(path = "/v1/sse/subscribe/{map_id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @GetMapping(path = "/v1/sse/subscribe/{sseId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe(@PathVariable String sseId) {
        //String sseId = UUID.randomUUID().toString();
        SseEmitter emitter = sseEmitterService.subscribe(sseId);
        return ResponseEntity.ok(emitter);
    }

    //eventPayload 를 SSE 로 연결된 모든 클라이언트에게 broadcasting 한다.
    @PostMapping(path = "/v1/sse/broadcast")
    public ResponseEntity<Void> broadcast(@RequestBody EventPayLoadDto eventPayLoadDto) throws JsonProcessingException {
        sseEmitterService.broadcast(eventPayLoadDto);
        return ResponseEntity.ok().build();

    }

}
