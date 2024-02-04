package com.kdu.smartHome.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomDTO {
    private String id;
    @JsonProperty("room_name")
    private String roomName;
}
