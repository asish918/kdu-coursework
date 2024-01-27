package com.kdu.caching.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LatLongData {
    private @Getter(AccessLevel.PUBLIC) String lat;
    private @Getter(AccessLevel.PUBLIC) String lon;
}
