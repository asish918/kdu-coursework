package com.kdu.smartHome.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HouseDTO {
    private String id;
    @JsonProperty("house_name")
    private String name;
    private String address;
}
