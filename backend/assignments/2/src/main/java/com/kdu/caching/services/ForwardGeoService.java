package com.kdu.caching.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdu.caching.dtos.AddressDTO;
import com.kdu.caching.dtos.ApiDTO;
import com.kdu.caching.dtos.LatLongDTO;
import com.kdu.caching.dtos.mappers.ApiToForwardResponse;
import com.kdu.caching.exceptions.InvalidAddressException;
import com.kdu.caching.repository.ForwardGeoRepository;

@Service
public class ForwardGeoService {
    @Autowired
    private ForwardGeoRepository forwardGeoRepository;

    public LatLongDTO findForwardGeocode(String address) throws InvalidAddressException {
        AddressDTO apiData = forwardGeoRepository.getForwardResponse(address);
        return ApiToForwardResponse.apiToForwardResponse(apiData);
    }
}
