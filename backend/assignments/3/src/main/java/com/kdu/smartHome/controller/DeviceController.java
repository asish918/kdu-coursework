package com.kdu.smartHome.controller;

import com.kdu.smartHome.dtos.DeviceDTO;
import com.kdu.smartHome.dtos.DeviceHouseDTO;
import com.kdu.smartHome.dtos.response.AllInventoryResponseDTO;
import com.kdu.smartHome.entity.Device;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DeviceController {
    private DeviceService deviceService;
    private HouseService houseService;

    public DeviceController(DeviceService deviceService, HouseService houseService) {
        this.deviceService = deviceService;
        this.houseService = houseService;
    }
    @PostMapping("/inventory")
    public ResponseEntity<String> addDeviceToInventory(@RequestBody DeviceDTO deviceDTO) {
        deviceService.addItemToInventory(deviceDTO);
        return new ResponseEntity<>("Item added to Inventory", HttpStatus.OK);
    }

    @PostMapping("/device/register")
    public ResponseEntity<String> registerDevice(@RequestBody DeviceDTO dto) throws InvalidCredentials, QueryEmptyException {
        deviceService.registerDevice(dto);
        return new ResponseEntity<>("Device Registered successfully", HttpStatus.OK);
    }

    @PostMapping("/device/add")
    public ResponseEntity<String> addDevice(@RequestBody DeviceHouseDTO dto) throws InvalidParamsException, DeviceNotRegistered, QueryEmptyException {
        ParamsValidator.requestParamValidator(dto.getHouseId());
        ParamsValidator.requestParamValidator(dto.getRoomId());
        houseService.addDeviceToHouse(dto);
        return new ResponseEntity<>("Device added to house successfully", HttpStatus.OK);
    }

    @GetMapping("/inventory")
    public ResponseEntity<AllInventoryResponseDTO> getAllDevices() {
        AllInventoryResponseDTO res = deviceService.getAllDevices();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
