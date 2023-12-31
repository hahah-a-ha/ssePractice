package org.example.sse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
@Slf4j
public class  SseEmitterService {
    private final Map<String, SseEmitter> emitterMap = new ConcurrentHashMap<>();

    private static final long TIMEOUT = 60 * 1000;
    private static final long RECONNECTION_TIMEOUT = 1000L;

    public SseEmitter subscribe(String id) {
        SseEmitter emitter = new SseEmitter(TIMEOUT);
        //연결 세션 timeout 이벤트 핸들러 등록
        emitter.onTimeout(() -> {
            log.info("server sent event timed out : id={}", id);
            //onCompletion 핸들러 호출
            emitter.complete();
        });

        //에러 핸들러 등록
        emitter.onError(e -> {
            log.info("server sent event error occurred : id={}, message={}", id, e.getMessage());
            //onCompletion 핸들러 호출
            emitter.complete();
        });

        //SSE complete 핸들러 등록
        emitter.onCompletion(() -> {
            if (emitterMap.remove(id) != null) {
                log.info("server sent event removed in emitter cache: id={}", id);
            }

            log.info("disconnected by completed server sent event: id={}", id);
        });

        emitterMap.put(id, emitter);

        //초기 연결시에 응답 데이터를 전송할 수도 있다.
//        try {
//            SseEmitter.SseEventBuilder event = SseEmitter.event()
//                    //event 명 (event: event example)
//                    .name("event example")
//                    //event id (id: id-1) - 재연결시 클라이언트에서 `Last-Event-ID` 헤더에 마지막 event id 를 설정
//                    .id(String.valueOf("id-1"))
//                    //event data payload (data: SSE connected)
//                    .data("SSE connected")
//                    //SSE 연결이 끊어진 경우 재접속 하기까지 대기 시간 (retry: <RECONNECTION_TIMEOUT>) //client는 server와 연결이 끊긴 경우, 1초 후 연결 가능.
//                    .reconnectTime(RECONNECTION_TIMEOUT);
//            emitter.send(event);
//        } catch (IOException e) {
//            log.error("failure send media position data, id={}, {}", id, e.getMessage());
//        }
        try{
            SseEmitter.SseEventBuilder event = SseEmitter.event().reconnectTime(RECONNECTION_TIMEOUT);
            emitter.send(event);
        } catch (IOException e){
            log.error("failure send media position data, {}",e.getMessage());
        }
        return emitter;
    }

//    public void broadcast(EventPayload eventPayload) {
//        emitterMap.forEach((id, emitter) -> {
//            try {
//                emitter.send(SseEmitter.event()
//                        //.name("broadcast event")
//                        //.id(id)
//                        //.reconnectTime(RECONNECTION_TIMEOUT)
//                        .data(eventPayload, MediaType.APPLICATION_JSON));
//                //log.info("sended notification, id={}, payload={}", id, eventPayload);
//            } catch (IOException e) {
//                //SSE 세션이 이미 해제된 경우
//                log.error("fail to send emitter id={}, {}", id, e.getMessage());
//            }
//        });
//        //emitterMap.get(eventPayload.toS).send
//    }
    private final ObjectMapper objectMapper = new ObjectMapper();
    public void broadcast(Object object) throws JsonProcessingException {

    emitterMap.forEach((id, emitter) -> {
        try {
            emitter.send(SseEmitter.event()
                    //.name("broadcast event")
                    //.id(id)
                    //.reconnectTime(RECONNECTION_TIMEOUT)
                    .data(objectMapper.writeValueAsString(object), MediaType.APPLICATION_JSON));
            //log.info("sended notification, id={}, payload={}", id, eventPayload);
        } catch (IOException e) {
            //SSE 세션이 이미 해제된 경우
            log.error("fail to send emitter id={}, {}", id, e.getMessage());
        }
    });
    //emitterMap.get(eventPayload.toS).send
}


    private SseEmitter createEmitter() {
        return new SseEmitter(TIMEOUT);
    }
}
