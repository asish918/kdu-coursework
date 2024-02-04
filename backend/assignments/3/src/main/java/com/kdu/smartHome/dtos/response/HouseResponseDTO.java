package com.kdu.smartHome.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HouseResponseDTO {
    @Data
    @AllArgsConstructor
    public static class HouseObj {
        private String id;
        private String houseName;
        private String address;
    }

    private String message;
    @JsonProperty("house")
    private HouseObj house;
    private int httpStatus;
}
