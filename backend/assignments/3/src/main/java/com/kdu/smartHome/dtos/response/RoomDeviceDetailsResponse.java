package com.kdu.smartHome.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RoomDeviceDetailsResponse {
    @Data
    @NoArgsConstructor
    public static class Obj {
        private String houseId;
        private String houseName;
        private String address;
        private String rooms;
        private String devices;
    }

    @JsonProperty("roomsAndDevices")
    private String roomsAndDevices;
}
