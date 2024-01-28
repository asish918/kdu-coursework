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
public class ApiDTO {
    @JsonProperty("data")
    private AddressDTO[] address;
}
