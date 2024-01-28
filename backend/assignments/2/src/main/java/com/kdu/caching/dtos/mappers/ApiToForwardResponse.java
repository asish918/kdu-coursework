package com.kdu.caching.dtos.mappers;

import com.kdu.caching.dtos.AddressDTO;
import com.kdu.caching.dtos.LatLongDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * Singleton Mapper implementation to Map the API response to
 * {@link com.kdu.caching.dtos.LatLongDTO Latitude and Longitude}
 */

@Slf4j
public class ApiToForwardResponse {
    private ApiToForwardResponse() {

    }
    public static LatLongDTO apiToForwardResponse(AddressDTO apiDTO) {
        return new LatLongDTO(Double.parseDouble(apiDTO.getLatitude()), Double.parseDouble(apiDTO.getLongitude()));
    }
}
