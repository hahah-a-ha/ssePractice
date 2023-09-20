package org.example.sse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@Getter
public class EventPayLoadDto {
    @JsonProperty("memberId")
    String memberId;
    @JsonProperty("memberName")
    String memberNam;
    @JsonProperty("memberAge")
    String memberAge;
}
