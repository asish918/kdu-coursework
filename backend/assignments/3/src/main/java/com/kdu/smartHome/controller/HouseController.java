package com.kdu.smartHome.controller;

import java.util.List;

import com.kdu.smartHome.dtos.HouseAddressDTO;
import com.kdu.smartHome.dtos.response.AllHouseResponseDTO;
import com.kdu.smartHome.dtos.response.HouseResponseDTO;
import com.kdu.smartHome.entity.Room;
import com.kdu.smartHome.exceptions.custom.InvalidParamsException;
import com.kdu.smartHome.exceptions.custom.NonAdminException;
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

@Controller
@RequestMapping("/api/v1")
@Slf4j
public class HouseController {
    private HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

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

    @GetMapping("/house/{houseId}")
    public ResponseEntity<List<Room>> getRoomDevices(@PathVariable String houseId) {
        log.info(houseId);
        List<Room> res = houseService.getHouseDetails(houseId);
        res.forEach(r -> log.info(r.toString()));

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/house/{houseId}/add-user")
    public ResponseEntity<String> addUserToHouse(@PathVariable String houseId, @RequestBody UserHouseDTO dto) throws NonAdminException, UserNotRegistered {
        houseService.addUserToHouse(houseId, dto);
        return new ResponseEntity<>("User added to house successfully", HttpStatus.OK);
    }

    @GetMapping("/house")
    public ResponseEntity<AllHouseResponseDTO> getAllHouses() {
        AllHouseResponseDTO res = houseService.getAllHouses();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/house")
    public ResponseEntity<String> updateHouseAddress(@RequestParam String houseId, @RequestBody HouseAddressDTO dto) throws InvalidParamsException {
        ParamsValidator.requestParamValidator(houseId);
        houseService.updateAddress(houseId, dto.getNewAddress());
        return new ResponseEntity<>("Address Updated successfully", HttpStatus.OK);
    }
}
