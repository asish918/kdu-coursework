package com.kdu.caching.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.kdu.caching.dtos.ApiDTO;
import com.kdu.caching.dtos.LatLongDTO;
import com.kdu.caching.services.ApiService;

/**
 * Repository Bean for Reverse Geocoding
 */
@Repository
public class ReverseGeoRepository {
    private ApiService apiService;

    @Autowired
    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     * The function is annotated with @Cacheable to make sure the results are
     * cached and no API call is made repeatedly.
     * @param latLongDTO DTO containing the Latitude and Longitude of the location
     * @return {@link com.kdu.caching.dtos.ApiDTO API Response}. We convert the double values
     * to String because the API call needs that.
     */
    @Cacheable("reverse-geocoding")
    public ApiDTO getReverseResponse(LatLongDTO latLongDTO) {
        double latitude = latLongDTO.getLatitude();
        double longitude = latLongDTO.getLongitude();
        return apiService.reverseGeoCodeFromApi(Double.toString(latitude), Double.toString(longitude));
    }
}
