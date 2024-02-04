package com.kdu.smartHome.controller;

import com.kdu.smartHome.dtos.HouseAddressDTO;
import com.kdu.smartHome.dtos.response.AllHouseResponseDTO;
import com.kdu.smartHome.dtos.response.HouseResponseDTO;
import com.kdu.smartHome.dtos.response.RoomDeviceDetailsResponse;
import com.kdu.smartHome.exceptions.custom.InvalidParamsException;
import com.kdu.smartHome.exceptions.custom.NonAdminException;
import com.kdu.smartHome.exceptions.custom.QueryEmptyException;
import com.kdu.smartHome.exceptions.custom.UserNotRegistered;
import com.kdu.smartHome.utils.ParamsValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.kdu.smartHome.dtos.HouseDTO;
import com.kdu.smartHome.dtos.UserHouseDTO;
import com.kdu.smartHome.entity.House;
import com.kdu.smartHome.service.HouseService;

/**
 * Endpoints for CRUD operations on Houses
 */
@Controller
@RequestMapping("/api/v1")
@Slf4j
public class HouseController {
    private HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * Create a house and return its details
     * @param dto Details of the house
     * @return Details of the house
     */
    @PostMapping("/house")
    public ResponseEntity<HouseResponseDTO> createHouse(@RequestBody HouseDTO dto) {
        House house = houseService.addHouse(dto);
        HouseResponseDTO res = new HouseResponseDTO(
                "House created successfully",
                new HouseResponseDTO.HouseObj(
                    house.getId().toString(),
                        house.getName(),
                        house.getAddress()
                ),
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Get all the details of a particular house, its rooms and all the devices within it.
     * @param houseId The id of the house to fetch
     * @return All the details of the house
     * @throws QueryEmptyException Incase the house doesn't exist
     */
    @GetMapping("/house/{houseId}")
    public ResponseEntity<RoomDeviceDetailsResponse> getRoomDevices(@PathVariable String houseId) throws QueryEmptyException {
        log.info(houseId);
        RoomDeviceDetailsResponse res = houseService.getHouseDetails(houseId);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Add a user to the house
     * @param houseId The id of the house in which the user will be added
     * @param dto The details of the user to be added
     * @return Confirmation for the addition of user into the house
     * @throws NonAdminException When a non-admin user of the house tries to add a user
     * @throws UserNotRegistered When an unregistered user tries to add something
     */
    @PostMapping("/house/{houseId}/add-user")
    public ResponseEntity<String> addUserToHouse(@PathVariable String houseId, @RequestBody UserHouseDTO dto) throws NonAdminException, UserNotRegistered {
        houseService.addUserToHouse(houseId, dto);
        return new ResponseEntity<>("User added to house successfully", HttpStatus.OK);
    }

    /**
     * Get all the registered houses
     * @return Details of all the registered houses
     */
    @GetMapping("/house")
    public ResponseEntity<AllHouseResponseDTO> getAllHouses() {
        AllHouseResponseDTO res = houseService.getAllHouses();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Endpoint to update the address of a particular house
     * @param houseId Id of the house to be updated
     * @param dto New Address
     * @return Confirmation of the updation
     * @throws InvalidParamsException Incase the HouseId is invalid
     */
    @PutMapping("/house")
    public ResponseEntity<String> updateHouseAddress(@RequestParam String houseId, @RequestBody HouseAddressDTO dto) throws InvalidParamsException {
        ParamsValidator.requestParamValidator(houseId);
        log.info(dto.toString());
        houseService.updateAddress(houseId, dto.getAddress());
        return new ResponseEntity<>("Address Updated successfully", HttpStatus.OK);
    }
}
