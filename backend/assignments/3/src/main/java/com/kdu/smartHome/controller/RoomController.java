package com.kdu.smartHome.controller;

import com.kdu.smartHome.dtos.RoomDTO;
import com.kdu.smartHome.dtos.response.RoomResponseDTO;
import com.kdu.smartHome.entity.Room;
import com.kdu.smartHome.exceptions.custom.InvalidParamsException;
import com.kdu.smartHome.exceptions.custom.QueryEmptyException;
import com.kdu.smartHome.service.RoomService;
import com.kdu.smartHome.utils.ParamsValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1")
public class RoomController {
    public RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @PostMapping("/room")
    public ResponseEntity<RoomResponseDTO> addRoom(@RequestParam String houseId, @RequestBody RoomDTO dto) throws InvalidParamsException, QueryEmptyException {
        ParamsValidator.requestParamValidator(houseId);
        Room room = roomService.addRoomToHouse(houseId, dto);
        RoomResponseDTO responseDTO = new RoomResponseDTO(
                "Room added to house successfully",
                new RoomResponseDTO.RoomObj(
                        room.getId().toString(),
                        room.getName()
                ),
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
