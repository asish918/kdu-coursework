package com.kdu.smartHome.controller;

import com.kdu.smartHome.dtos.DeviceDTO;
import com.kdu.smartHome.dtos.DeviceHouseDTO;
import com.kdu.smartHome.dtos.response.AllInventoryResponseDTO;
import com.kdu.smartHome.exceptions.custom.DeviceNotRegistered;
import com.kdu.smartHome.exceptions.custom.InvalidCredentials;
import com.kdu.smartHome.exceptions.custom.InvalidParamsException;
import com.kdu.smartHome.exceptions.custom.QueryEmptyException;
import com.kdu.smartHome.service.DeviceService;
import com.kdu.smartHome.service.HouseService;
import com.kdu.smartHome.utils.ParamsValidator;
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
    @GetMapping("/inventory")
    public ResponseEntity<AllInventoryResponseDTO> getAllDevices() {
        AllInventoryResponseDTO res = deviceService.getAllDevices();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
