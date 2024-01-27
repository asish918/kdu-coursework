package com.kdu.caching.controller;

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

@Slf4j
@RestController
@Validated
public class AppController {
    @Autowired
    private ForwardGeoService forwardGeoService;
    @Autowired
    private ReverseGeoService reverseGeoService;

    @GetMapping("/test")
    public ResponseEntity<String> testGet() {
        log.info("Test GET Request successful. Server is working fine....");
        return new ResponseEntity<>("Test GET Request successful. Server is working fine....", HttpStatus.OK);
    }

    @GetMapping("/geocoding")
    public ResponseEntity<LatLongDTO> getForwardGeocoding(@RequestParam String address) throws InvalidAddressException {
        log.info(address);
        LatLongDTO res = forwardGeoService.findForwardGeocode(address);
        return new ResponseEntity<>(res,
                HttpStatus.OK);
    }

    @GetMapping("/reverse-geocoding")
    public ResponseEntity<String> getReverseGeocoding(@RequestParam String latitude, @RequestParam String longitude)
            throws InvalidParamsException {
        Validator.validateParams(latitude, longitude);
        log.info(latitude + " " + longitude);
        String res = reverseGeoService.findReverseGeocode(new LatLongDTO(Double.parseDouble(latitude), Double.parseDouble(longitude)));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
