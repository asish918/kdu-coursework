package com.kdu.caching.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * The Model for Request Params
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LatLongData {
    private String lat;
    private String lon;
}
