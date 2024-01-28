package com.kdu.caching.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RestController;

import com.kdu.caching.dtos.LatLongDTO;
import com.kdu.caching.exceptions.InvalidAddressException;
import com.kdu.caching.exceptions.InvalidParamsException;
import com.kdu.caching.services.ForwardGeoService;
import com.kdu.caching.services.ReverseGeoService;
import com.kdu.caching.utils.Validator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The main REST Controller for the entire app.
 */
@Slf4j
@RestController
@Validated
public class AppController {
    private ForwardGeoService forwardGeoService;
    private ReverseGeoService reverseGeoService;

    @Autowired
    public AppController(ForwardGeoService forwardGeoService, ReverseGeoService reverseGeoService) {
        this.forwardGeoService = forwardGeoService;
        this.reverseGeoService = reverseGeoService;
    }

    /**
     * A test endpoint to make sure the server works fine
     * @return Response Entity
     */
    @Operation(summary = "Test the Server", description = "Returns a sample response to confirm a working server")
    @GetMapping("/test")
    public ResponseEntity<String> testGet() {
        log.info("Test GET Request successful. Server is working fine....");
        return new ResponseEntity<>("Test GET Request successful. Server is working fine....", HttpStatus.OK);
    }

    /**
     * Forward Geocoding endpoint
     * @param address Request Params address to fetch lat and long
     * @return {@link com.kdu.caching.dtos.LatLongDTO Latitude and Longitude} response
     * @throws InvalidAddressException If no data for the address can be found.
     */
    @Operation(summary = "Forward Geocoding endpoint", description = "Returns Latitude and Longitude for a given address")
    @GetMapping("/geocoding")
    public ResponseEntity<LatLongDTO> getForwardGeocoding(@RequestParam String address) throws InvalidAddressException {
        log.info(address);
        LatLongDTO res = forwardGeoService.findForwardGeocode(address);
        return new ResponseEntity<>(res,
                HttpStatus.OK);
    }

    /**
     * Reverse Geocoding endpoint
     * @param latitude Latitude
     * @param longitude Longitude
     * @return Address String
     * @throws InvalidParamsException If the params are not numbers.
     */
    @Operation(summary = "Reverse Geocoding endpoint", description = "Returns address for a given pair of latitude and longitude")
    @GetMapping("/reverse-geocoding")
    public ResponseEntity<String> getReverseGeocoding(@RequestParam String latitude, @RequestParam String longitude)
            throws InvalidParamsException {
        Validator.validateParams(latitude, longitude);
        log.info(latitude + " " + longitude);
        String res = reverseGeoService.findReverseGeocode(new LatLongDTO(Double.parseDouble(latitude), Double.parseDouble(longitude)));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
