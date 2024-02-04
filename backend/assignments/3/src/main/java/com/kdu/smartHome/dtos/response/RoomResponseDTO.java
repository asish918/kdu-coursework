package com.kdu.smartHome.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomResponseDTO {
    @Data
    @AllArgsConstructor
    public static class RoomObj {
        private String id;
        private String name;
    }

    private String message;
    @JsonProperty
    private RoomObj room;
    private int httpStatus;
}
