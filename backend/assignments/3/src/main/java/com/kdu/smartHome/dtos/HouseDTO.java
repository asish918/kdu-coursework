package com.kdu.smartHome.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HouseDTO {
    @JsonProperty("house_name")
    private String houseName;
    private String address;
}
