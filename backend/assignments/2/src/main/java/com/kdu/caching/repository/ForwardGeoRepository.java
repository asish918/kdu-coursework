package com.kdu.caching.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.kdu.caching.dtos.AddressDTO;
import com.kdu.caching.dtos.ApiDTO;
import com.kdu.caching.exceptions.InvalidAddressException;
import com.kdu.caching.services.ApiService;

@Repository
public class ForwardGeoRepository {
    @Autowired
    private ApiService apiService;

    @Cacheable(value = "geocoding", condition = "!#address.equalsIgnoreCase('goa')")
    public AddressDTO getForwardResponse(String address) throws InvalidAddressException {
        ApiDTO apiData = apiService.forwardGeoCodeFromApi(address);

        if (apiData.getAddress().length == 0) {
            throw new InvalidAddressException("Invalid Address", new IllegalArgumentException());
        }

        return apiData.getAddress()[0];
    }
}
