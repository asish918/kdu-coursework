package com.kdu.caching.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.kdu.caching.dtos.ApiDTO;
import com.kdu.caching.dtos.LatLongDTO;
import com.kdu.caching.services.ApiService;

@Repository
public class ReverseGeoRepository {
    @Autowired
    private ApiService apiService;

    @Cacheable("reverse-geocoding")
    public ApiDTO getReverseResponse(LatLongDTO latLongDTO) {
        double latitude = latLongDTO.getLatitude();
        double longitude = latLongDTO.getLongitude();
        return apiService.reverseGeoCodeFromApi(Double.toString(latitude), Double.toString(longitude));
    }
}
