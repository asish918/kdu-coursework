package com.kdu.smarthome.controller;

import com.kdu.smarthome.dtos.DeviceDTO;
import com.kdu.smarthome.dtos.DeviceHouseDTO;
import com.kdu.smarthome.dtos.response.AllInventoryResponseDTO;
import com.kdu.smarthome.exceptions.custom.DeviceNotRegistered;
import com.kdu.smarthome.exceptions.custom.InvalidCredentials;
import com.kdu.smarthome.exceptions.custom.InvalidParamsException;
import com.kdu.smarthome.exceptions.custom.QueryEmptyException;
import com.kdu.smarthome.service.DeviceService;
import com.kdu.smarthome.service.HouseService;
import com.kdu.smarthome.utils.ParamsValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints for managing the entire inventory
 */
@RestController
@RequestMapping("/api/v1")
public class DeviceController {
    private DeviceService deviceService;
    private HouseService houseService;

    public DeviceController(DeviceService deviceService, HouseService houseService) {
        this.deviceService = deviceService;
        this.houseService = houseService;
    }

    /**
     * Add a device to the inventory
     * @param deviceDTO Details of the device to be added
     * @return Confirmation of the added Item
     */
    @Operation(summary = "Add device to inventory", description = "Add a new device to the inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item added to Inventory"),
    }
    )
    @PostMapping("/inventory")
    public ResponseEntity<String> addDeviceToInventory(@RequestBody DeviceDTO deviceDTO) {
        deviceService.addItemToInventory(deviceDTO);
        return new ResponseEntity<>("Item added to Inventory", HttpStatus.OK);
    }

    /**
     * Endpoint to validate the credentials
     * and register the device.
     * @param dto Details of the device including username and password
     * @return Confirmation for the registered device
     * @throws InvalidCredentials Incase the credentials are invalid
     * @throws QueryEmptyException Incase the device doesn't exist in the first place
     */
    @Operation(summary = "Register a device", description = "Register a device using username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device Registered successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid device credentials"),
            @ApiResponse(responseCode = "400", description = "Device doesn't exist"),
    }
    )
    @PostMapping("/device/register")
    public ResponseEntity<String> registerDevice(@RequestBody DeviceDTO dto) throws InvalidCredentials, QueryEmptyException {
        deviceService.registerDevice(dto);
        return new ResponseEntity<>("Device Registered successfully", HttpStatus.OK);
    }

    /**
     * Endpoint to add a new device to a House
     * @param dto Credentials for the device and ids of Room and House
     * @return Confirmation for the added device.
     * @throws InvalidParamsException Incase the houseId and roomId are invalid
     * @throws DeviceNotRegistered Incacse the device is not registered
     * @throws QueryEmptyException Incase the device doesn't exist in first place
     */
    @Operation(summary = "Add device to house", description = "Add a registered device to a valid house")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device added successfully"),
            @ApiResponse(responseCode = "401", description = "Device not registered"),
            @ApiResponse(responseCode = "400", description = "Device/House doesn't exist"),
    }
    )
    @PostMapping("/device/add")
    public ResponseEntity<String> addDevice(@RequestBody DeviceHouseDTO dto) throws InvalidParamsException, DeviceNotRegistered, QueryEmptyException {
        ParamsValidator.requestParamValidator(dto.getHouseId());
        ParamsValidator.requestParamValidator(dto.getRoomId());
        houseService.addDeviceToHouse(dto);
        return new ResponseEntity<>("Device added to house successfully", HttpStatus.OK);
    }

    /**
     * Get all the products in the inventory
     * @return List of devices in the inventory
     */
    @Operation(summary = "Get all devices", description = "Get all the devices in the inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device Details"),
            @ApiResponse(responseCode = "401", description = "Invalid device credentials"),
            @ApiResponse(responseCode = "400", description = "Device doesn't exist"),
    }
    )
    @GetMapping("/inventory")
    public ResponseEntity<AllInventoryResponseDTO> getAllDevices() {
        AllInventoryResponseDTO res = deviceService.getAllDevices();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
