package org.example.sse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

//@Getter
public record EventPayload(@JsonProperty("memberId") String memberId,
                           @JsonProperty("memberName") String memberName,
                           @JsonProperty("memberAge") String memberAge) {
}
