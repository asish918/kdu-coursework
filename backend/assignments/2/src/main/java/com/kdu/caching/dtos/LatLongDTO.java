package com.kdu.caching.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO for handling the latitude and longitude of a given address.
 */
@Data
@AllArgsConstructor
public class LatLongDTO {
    private double latitude;
    private double longitude;
}
