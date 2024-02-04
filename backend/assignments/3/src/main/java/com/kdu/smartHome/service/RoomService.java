package com.kdu.smartHome.service;

import com.kdu.smartHome.dtos.RoomDTO;
import com.kdu.smartHome.dtos.mappers.RoomMapper;
import com.kdu.smartHome.entity.House;
import com.kdu.smartHome.entity.Room;
import com.kdu.smartHome.exceptions.custom.QueryEmptyException;
import com.kdu.smartHome.repository.HouseRepository;
import com.kdu.smartHome.repository.RoomRepository;
import com.kdu.smartHome.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service layer for {@link com.kdu.smartHome.entity.Room RoomEntity}
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
