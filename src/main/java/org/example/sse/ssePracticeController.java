//package org.example.sse;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.ConcurrentHashMap;
//
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//public class ssePracticeController {
//    private final ssePracticeService ssePracticeService;
//
//    // 응답 mime type은 반드시 text/event-stream이어야 합니다.
//    // 클라이언트로부터 SSE subscription을 수락합니다.
//    @GetMapping(path = "/v1/sse/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public ResponseEntity<SseEmitter> subscribe() {
//        String sseId = UUID.randomUUID().toString();
//        SseEmitter emitter = ssePracticeService.subscribe(sseId);
////        try {
////            SseEmitter.SseEventBuilder event = SseEmitter.event()
////                    .data("SSE connected");
////            emitter.send(event);
////        } catch (IOException e) {
////            log.error("Failure to send initial data to client, id={}, {}", sseId, e.getMessage());
////        }
//        return ResponseEntity.ok(emitter);
//    }
//}
