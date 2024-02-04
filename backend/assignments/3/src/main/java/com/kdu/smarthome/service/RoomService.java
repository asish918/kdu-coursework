package com.kdu.smarthome.service;

import com.kdu.smarthome.dtos.RoomDTO;
import com.kdu.smarthome.dtos.mappers.RoomMapper;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exceptions.custom.QueryEmptyException;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.RoomRepository;
import com.kdu.smarthome.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service layer for {@link com.kdu.smarthome.entity.Room RoomEntity}
 */
@Service
public class RoomService {
    private RoomRepository roomRepository;
    private HouseRepository houseRepository;
    private UserInfoRepository userInfoRepository;
    private RoomMapper roomMapper;

    @Autowired
    public RoomService(RoomRepository roomRepository, HouseRepository houseRepository, RoomMapper roomMapper, UserInfoRepository userInfoRepository) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
        this.roomMapper = roomMapper;
        this.userInfoRepository = userInfoRepository;
    }

    public Room addRoomToHouse(String houseId, RoomDTO roomDTO) throws QueryEmptyException {
        Room room = roomMapper.dtoToEntity(roomDTO);

        Optional<House> res = houseRepository.findById(Long.parseLong(houseId));
        Room roomRes = null;

        if (res.isPresent()) {
            House house = res.get();
            house.getRooms().add(room);
            roomRes = roomRepository.save(room);
            houseRepository.save(house);
        } else {
            throw new QueryEmptyException("House not found in database", new Exception());
        }

        return roomRes;
    }
}
