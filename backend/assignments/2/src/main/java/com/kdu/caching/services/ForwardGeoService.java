package com.kdu.caching.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdu.caching.dtos.AddressDTO;
import com.kdu.caching.dtos.LatLongDTO;
import com.kdu.caching.dtos.mappers.ApiToForwardResponse;
import com.kdu.caching.exceptions.InvalidAddressException;
import com.kdu.caching.repository.ForwardGeoRepository;

/**
 * Service Bean for Forward Geocoding
 */
@Service
public class ForwardGeoService {
    private ForwardGeoRepository forwardGeoRepository;

    @Autowired
    public void setForwardGeoRepository(ForwardGeoRepository forwardGeoRepository) {
        this.forwardGeoRepository = forwardGeoRepository;
    }

    /**
     * Passes the Address string to the Repository to get the
     * Latitude and longitude of the given address.
     * @param address Location to get the co-ordinates of
     * @return {@link com.kdu.caching.dtos.LatLongDTO Co-ordinates} of the address
     * @throws InvalidAddressException If co-ordinates of the location couldn't be found.
     */
    public LatLongDTO findForwardGeocode(String address) throws InvalidAddressException {
        AddressDTO apiData = forwardGeoRepository.getForwardResponse(address);
        return ApiToForwardResponse.apiToForwardResponse(apiData);
    }
}
