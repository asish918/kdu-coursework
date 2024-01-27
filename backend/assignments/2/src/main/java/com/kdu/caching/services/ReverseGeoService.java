package com.kdu.caching.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdu.caching.dtos.ApiDTO;
import com.kdu.caching.dtos.LatLongDTO;
import com.kdu.caching.repository.ReverseGeoRepository;

@Service
public class ReverseGeoService {
    @Autowired
    private ReverseGeoRepository reverseGeoRepository;

    public String findReverseGeocode(LatLongDTO latLongDTO) {
        ApiDTO apiData = reverseGeoRepository.getReverseResponse(latLongDTO);
        return apiData.getAddress()[0].getLabel();
    }
}
