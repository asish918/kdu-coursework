package com.kdu.smartHome.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.kdu.smartHome.dtos.DeviceHouseDTO;
import com.kdu.smartHome.dtos.response.AllHouseResponseDTO;
import com.kdu.smartHome.entity.Device;
import com.kdu.smartHome.entity.Room;
import com.kdu.smartHome.entity.maps.AdminTable;
import com.kdu.smartHome.exceptions.custom.*;
import com.kdu.smartHome.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kdu.smartHome.dtos.HouseDTO;
import com.kdu.smartHome.dtos.UserHouseDTO;
import com.kdu.smartHome.dtos.mappers.HouseMapper;
import com.kdu.smartHome.entity.House;
import com.kdu.smartHome.entity.UserInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HouseService {
    private HouseRepository houseRepository;
    private DeviceRepository deviceRepository;
    private RoomRepository roomRepository;
    private HouseMapper houseMapper;
    private UserInfoRepository userInfoRepository;

    private AdminRepository adminRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository, HouseMapper houseMapper,
            UserInfoRepository userInfoRepository, AdminRepository adminRepository, RoomRepository roomRepository, DeviceRepository deviceRepository) {
        this.houseRepository = houseRepository;
        this.houseMapper = houseMapper;
        this.userInfoRepository = userInfoRepository;
        this.adminRepository = adminRepository;
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
    }

    public House addHouse(HouseDTO houseDTO) {
        House house = houseMapper.dtoToEntity(houseDTO);
        House houseRes = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String adminName = authentication.getName();

        Optional<UserInfo> res = userInfoRepository.findByUsername(adminName);

        if (res.isPresent()) {
            house.getUsers().add(res.get());
            AdminTable adminTable = new AdminTable();

            adminTable.setHouse(house);
            adminTable.setUser(res.get());

            houseRes = houseRepository.save(house);
            adminRepository.save(adminTable);
        }

        return houseRes;
    }

    public List<Room> getHouseDetails(String houseId) {
        Optional<House> res = houseRepository.findById(Long.parseLong(houseId));
        log.info(res.get().toString());
        return res.map(House::getRooms).orElse(null);
    }

    public void addUserToHouse(String houseId, UserHouseDTO userHouseDTO) throws NonAdminException, UserNotRegistered {
         Authentication authentication =
         SecurityContextHolder.getContext().getAuthentication();
         String adminName = authentication.getName();

         log.info(adminName);

        Optional<UserInfo> adminRes = userInfoRepository.findByUsername(adminName);
        Optional<House> houseRes = houseRepository.findById(Long.parseLong(houseId));
        Optional<AdminTable> adminId = Optional.empty();

        if(houseRes.isPresent()) {
            adminId = adminRepository.findByHouseEquals(houseRes.get());
        }

        if(adminRes.isEmpty() || adminId.isPresent() && !Objects.equals(adminId.get().getUser().getId(), adminRes.get().getId())) {
            throw new NonAdminException("User is not admin", new Exception());
        }

        Optional<UserInfo> userRes = userInfoRepository.findByUsername(userHouseDTO.getUsername());

        if (houseRes.isPresent() && userRes.isPresent()) {
            House house = houseRes.get();
            UserInfo userInfo = userRes.get();
            house.getUsers().add(userInfo);

            houseRepository.save(house);
        } else {
            throw new UserNotRegistered("User is not registered", new Exception());
        }
    }

    public AllHouseResponseDTO getAllHouses() {
        AllHouseResponseDTO res = new AllHouseResponseDTO();
        List<House> houseList = houseRepository.findAll();
        res.setHouses(houseList);
        return res;
    }

    public void updateAddress(String id, String address) {
        houseRepository.updateAddressByIdEquals(address, Long.parseLong(id));
    }

    public void addDeviceToHouse(DeviceHouseDTO dto) throws DeviceNotRegistered, QueryEmptyException {
        Optional<House> houseRes = houseRepository.findById(Long.parseLong(dto.getHouseId()));
        Optional<Room> roomRes = roomRepository.findById(Long.parseLong(dto.getRoomId()));
        Optional<Device> deviceRes = deviceRepository.findById(Long.parseLong(dto.getKickstonId()));

        if(houseRes.isPresent() && roomRes.isPresent() && deviceRes.isPresent() && deviceRes.get().getRegistered()) {
            Room room = roomRes.get();
            Device device = deviceRes.get();
            device.setRoom(room);
            deviceRepository.save(device);
            room.getDevices().add(device);
            roomRepository.save(room);
        } else if(deviceRes.isEmpty()) {
            throw new QueryEmptyException("No such device", new Exception());
        }
        else if(!deviceRes.get().getRegistered()) {
            throw new DeviceNotRegistered("Device is not Registered", new Exception());
        }
    }
}
