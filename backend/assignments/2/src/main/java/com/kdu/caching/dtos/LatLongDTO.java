package com.kdu.caching.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class LatLongDTO {
    private @Getter(AccessLevel.PUBLIC) double latitude;
    private @Getter(AccessLevel.PUBLIC) double longitude;
}
