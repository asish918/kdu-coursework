package com.kdu.caching.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {
    private String latitude;
    private String longitude;
    private String name;
    private String country;
    private String country_code;
    private String continent;
    private String label;

    @Override
    public String toString() {
        return name + ", " + country + ", " + continent + ", " + country_code;
    }
}
