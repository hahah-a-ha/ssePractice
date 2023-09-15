//package org.example.sse;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//
//@Service
//@Slf4j
//public class ssePracticeService {
//    private final Map<String, SseEmitter> emitterMap = new ConcurrentHashMap<>();
//
//    private static final long TIMEOUT = 120 * 1000;
//    private static final long RECONNECTION_TIMEOUT = 1000L;
//
//    public SseEmitter subscribe(String id) {
//        SseEmitter emitter = new SseEmitter(TIMEOUT);
//        // 연결 세션 timeout 이벤트 핸들러 등록
//        emitter.onTimeout(() -> {
//            log.info("server sent event timed out: id={}", id);
//            // onCompletion 핸들러 호출
//            emitter.complete();
//        });
//
//        // 에러 핸들러 등록
//        emitter.onError(e -> {
//            log.info("server sent event error occurred: id={}, message={}", id, e.getMessage());
//            // onCompletion 핸들러 호출
//            emitter.complete();
//        });
//
//        // SSE complete 핸들러 등록
//        emitter.onCompletion(() -> {
//            if (emitterMap.remove(id) != null) {
//                log.info("server sent event removed in emitter cache: id={}", id);
//            }
//
//            log.info("disconnected by completed server sent event: id={}", id);
//        });
//
//        emitterMap.put(id, emitter);
//        try{
//            //SSE 연결이 끊어진 경우 재접속 하기까지 대기 시간 (retry: <RECONNECTION_TIMEOUT>) //client는 server와 연결이 끊긴 경우, 1초 후 연결 가능.
//            //SseEmitter.SseEventBuilder event = SseEmitter.event().reconnectTime(RECONNECTION_TIMEOUT);
//            SseEmitter.SseEventBuilder event = SseEmitter.event()
//                    //.reconnectTime(RECONNECTION_TIMEOUT) //이게 필요한가?
//                    .data("SSE connected"); //error 메세지 보내는 부분.
//            emitter.send(event);
//       } catch (IOException e){
//           log.error("failure send media position data, {}",e.getMessage());
//        }
//
//        return emitter;
//    }
////    private SseEmitter createEmitter() {
////        return new SseEmitter(TIMEOUT);
////    }
//
//}
