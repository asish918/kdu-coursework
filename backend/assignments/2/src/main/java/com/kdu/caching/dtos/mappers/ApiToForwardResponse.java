package com.kdu.caching.dtos.mappers;

import com.kdu.caching.dtos.AddressDTO;
import com.kdu.caching.dtos.ApiDTO;
import com.kdu.caching.dtos.LatLongDTO;

public class ApiToForwardResponse {
    public static LatLongDTO apiToForwardResponse(AddressDTO apiDTO) {
        return new LatLongDTO(Double.parseDouble(apiDTO.getLatitude()), Double.parseDouble(apiDTO.getLongitude()));
    }
}
