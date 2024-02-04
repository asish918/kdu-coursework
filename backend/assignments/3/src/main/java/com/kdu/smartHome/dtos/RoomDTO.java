package com.kdu.smartHome.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoomDTO {
    @JsonProperty("room_name")
    private String roomName;
}
