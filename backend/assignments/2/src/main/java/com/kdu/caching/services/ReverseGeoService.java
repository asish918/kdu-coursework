package com.kdu.caching.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdu.caching.dtos.ApiDTO;
import com.kdu.caching.dtos.LatLongDTO;
import com.kdu.caching.repository.ReverseGeoRepository;

/**
 * Service Bean for Reverse Geocoding
 */
@Service
public class ReverseGeoService {
    private ReverseGeoRepository reverseGeoRepository;
    @Autowired
    public void setReverseGeoRepository(ReverseGeoRepository reverseGeoRepository) {
        this.reverseGeoRepository = reverseGeoRepository;
    }

    /**
     * Takes Latitude and Longitude for a given location and passes it
     * on to the Repository to get the address.
     * @param latLongDTO DTO of Latitude and Longitude
     * @return Address as a String
     */
    public String findReverseGeocode(LatLongDTO latLongDTO) {
        ApiDTO apiData = reverseGeoRepository.getReverseResponse(latLongDTO);
        return apiData.getAddress()[0].getLabel();
    }
}
