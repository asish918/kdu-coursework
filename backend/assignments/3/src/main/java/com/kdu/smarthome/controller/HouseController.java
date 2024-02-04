package com.kdu.smarthome.controller;

import com.kdu.smarthome.dtos.HouseAddressDTO;
import com.kdu.smarthome.dtos.response.AllHouseResponseDTO;
import com.kdu.smarthome.dtos.response.HouseResponseDTO;
import com.kdu.smarthome.dtos.response.RoomDeviceDetailsResponse;
import com.kdu.smarthome.exceptions.custom.InvalidParamsException;
import com.kdu.smarthome.exceptions.custom.NonAdminException;
import com.kdu.smarthome.exceptions.custom.QueryEmptyException;
import com.kdu.smarthome.exceptions.custom.UserNotRegistered;
import com.kdu.smarthome.utils.ParamsValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.kdu.smarthome.dtos.HouseDTO;
import com.kdu.smarthome.dtos.UserHouseDTO;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.service.HouseService;

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
    @Operation(summary = "Create a house", description = "Create a house with admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "House created successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid Auth Token"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    }
    )
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
    @Operation(summary = "Get room device details", description = "Get all rooms for a house and its device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room Device Details"),
            @ApiResponse(responseCode = "401", description = "Invalid Auth Token"),
            @ApiResponse(responseCode = "400", description = "House doesn't exist"),
    }
    )
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
    @Operation(summary = "Add a user to house")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User added to house successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid Auth Token"),
            @ApiResponse(responseCode = "400", description = "House doesn't exist"),
    }
    )
    @PostMapping("/house/{houseId}/add-user")
    public ResponseEntity<String> addUserToHouse(@PathVariable String houseId, @RequestBody UserHouseDTO dto) throws NonAdminException, UserNotRegistered {
        houseService.addUserToHouse(houseId, dto);
        return new ResponseEntity<>("User added to house successfully", HttpStatus.OK);
    }

    /**
     * Get all the registered houses
     * @return Details of all the registered houses
     */
    @Operation(summary = "Get all houses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of houses"),
            @ApiResponse(responseCode = "401", description = "Invalid Auth Token"),
    }
    )
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
    @Operation(summary = "Update house address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address Updated successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid Auth Token"),
    }
    )
    @PutMapping("/house")
    public ResponseEntity<String> updateHouseAddress(@RequestParam String houseId, @RequestBody HouseAddressDTO dto) throws InvalidParamsException {
        ParamsValidator.requestParamValidator(houseId);
        log.info(dto.toString());
        houseService.updateAddress(houseId, dto.getAddress());
        return new ResponseEntity<>("Address Updated successfully", HttpStatus.OK);
    }
}
