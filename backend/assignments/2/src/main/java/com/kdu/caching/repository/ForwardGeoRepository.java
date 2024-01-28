package com.kdu.caching.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.kdu.caching.dtos.AddressDTO;
import com.kdu.caching.dtos.ApiDTO;
import com.kdu.caching.exceptions.InvalidAddressException;
import com.kdu.caching.services.ApiService;

/**
 * Repository Bean for Forward Geocoding
 */
@Repository
public class ForwardGeoRepository {
    private ApiService apiService;

    @Autowired
    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     * The function is annotated with @Cacheable to make sure the results are
     * cached and no API call is made repeatedly. The condition parameter specifies the
     * SPEL expression for ignoring the address of goa. The response is an Array so we return
     * the very first element of the array.
     * @param address Address to find the co-ordinates of
     * @return {@link com.kdu.caching.dtos.AddressDTO API Response}
     * @throws InvalidAddressException If no information for the given address can be found.
     */
    @Cacheable(value = "geocoding", condition = "!#address.equalsIgnoreCase('goa')")
    public AddressDTO getForwardResponse(String address) throws InvalidAddressException {
        ApiDTO apiData = apiService.forwardGeoCodeFromApi(address);

        if (apiData.getAddress().length == 0) {
            throw new InvalidAddressException("Invalid Address", new IllegalArgumentException());
        }

        return apiData.getAddress()[0];
    }
}
