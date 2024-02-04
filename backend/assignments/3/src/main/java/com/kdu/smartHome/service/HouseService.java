package com.kdu.smartHome.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.kdu.smartHome.dtos.*;
import com.kdu.smartHome.dtos.mappers.DeviceMapper;
import com.kdu.smartHome.dtos.mappers.RoomMapper;
import com.kdu.smartHome.dtos.response.AllHouseResponseDTO;
import com.kdu.smartHome.dtos.response.RoomDeviceDetailsResponse;
import com.kdu.smartHome.entity.Device;
import com.kdu.smartHome.entity.Room;
import com.kdu.smartHome.entity.maps.AdminTable;
import com.kdu.smartHome.exceptions.custom.*;
import com.kdu.smartHome.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kdu.smartHome.dtos.mappers.HouseMapper;
import com.kdu.smartHome.entity.House;
import com.kdu.smartHome.entity.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * Service layer for {@link com.kdu.smartHome.entity.House HouseEntity}
 */
@Service
@Slf4j
public class HouseService {
    private HouseRepository houseRepository;
    private DeviceRepository deviceRepository;
    private RoomRepository roomRepository;
    private HouseMapper houseMapper;
    private UserInfoRepository userInfoRepository;

    private AdminRepository adminRepository;

    private RoomMapper roomMapper;

    @Autowired
    public HouseService(HouseRepository houseRepository, HouseMapper houseMapper,
            UserInfoRepository userInfoRepository, AdminRepository adminRepository, RoomRepository roomRepository, DeviceRepository deviceRepository, RoomMapper roomMapper) {
        this.houseRepository = houseRepository;
        this.houseMapper = houseMapper;
        this.userInfoRepository = userInfoRepository;
        this.adminRepository = adminRepository;
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
        this.roomMapper = roomMapper;
    }

    /**
     * Add a house and set the admin by finding the user from authentication context
     * @param houseDTO Details of the house
     * @return Details of the created House
     */

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

    /**
     * Get details of a particular house
     * Find its rooms and devices and set up the DTO
     * @param houseId House to fetch details of
     * @return DTO Response
     * @throws QueryEmptyException If the house doesn't exist
     */
    public RoomDeviceDetailsResponse getHouseDetails(String houseId) throws QueryEmptyException {
        Optional<House> houseRes = houseRepository.findById(Long.parseLong(houseId));

        if(houseRes.isEmpty()) throw new QueryEmptyException("No House found", new Exception());

        RoomDeviceDetailsResponse res = new RoomDeviceDetailsResponse();
        RoomDeviceDetailsResponse.Obj innerRes = new RoomDeviceDetailsResponse.Obj();
        innerRes.setHouseId(houseRes.get().getId().toString());
        innerRes.setHouseName(houseRes.get().getName());
        innerRes.setAddress(houseRes.get().getAddress());

        List<RoomDTO> roomDTOList = houseRes.get().getRooms().stream().map(r -> roomMapper.entityToDTO(r)).toList();

        innerRes.setRooms(roomDTOList.toString());

        List<Device> deviceList = new ArrayList<>();
        houseRes.get().getRooms().forEach(r -> deviceList.addAll(r.getDevices()));
        List<DeviceDTO> deviceDTOList = deviceList.stream().map(DeviceMapper::entityToDTO).toList();

        innerRes.setDevices(deviceDTOList.toString());

        res.setRoomsAndDevices(innerRes.toString());
        return res;
    }

    /**
     * Add a user to particular house
     * @param houseId id os the house
     * @param userHouseDTO details of the user to be added
     * @throws NonAdminException When a non-admin tries to add a user
     * @throws UserNotRegistered When an admin tries to add unregistered users to the house
     */
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
        String dtoList = houseList.stream().map(h -> houseMapper.entityToDTO(h)).toList().toString();
        res.setHouses(dtoList);
        return res;
    }

    public void updateAddress(String id, String address) {
        houseRepository.updateAddressByIdEquals(address, Long.parseLong(id));
    }

    /**
     * Add a device to the house if it is a registered device
     * @param dto Request DTO
     * @throws DeviceNotRegistered Incase the device is not registered
     * @throws QueryEmptyException Incase the device itself doesn't exist
     */
    public void addDeviceToHouse(DeviceHouseDTO dto) throws DeviceNotRegistered, QueryEmptyException {
        Optional<House> houseRes = houseRepository.findById(Long.parseLong(dto.getHouseId()));
        Optional<Room> roomRes = roomRepository.findById(Long.parseLong(dto.getRoomId()));
        Optional<Device> deviceRes = deviceRepository.findById(Long.parseLong(dto.getKickstonId()));

        if(houseRes.isPresent() && roomRes.isPresent() && deviceRes.isPresent() && Boolean.TRUE.equals(deviceRes.get().getRegistered())) {
            Room room = roomRes.get();
            Device device = deviceRes.get();
            device.setRoom(room);
            deviceRepository.save(device);
            room.getDevices().add(device);
            roomRepository.save(room);
        } else if(deviceRes.isEmpty()) {
            throw new QueryEmptyException("No such device", new Exception());
        }
        else if(Boolean.FALSE.equals(deviceRes.get().getRegistered())) {
            throw new DeviceNotRegistered("Device is not Registered", new Exception());
        }
    }
}
