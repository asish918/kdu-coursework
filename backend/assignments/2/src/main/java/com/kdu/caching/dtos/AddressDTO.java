package com.kdu.caching.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * DTO for handling the API Response for a given address.
 * The @JsonProperty indicates Jackson Library to parse it as a object
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {
    private String latitude;
    private String longitude;
    private String name;
    private String country;
    @JsonProperty("country_code")
    private String countryCode;
    private String continent;
    private String label;

    @Override
    public String toString() {
        return name + ", " + country + ", " + continent + ", " + countryCode;
    }
}
