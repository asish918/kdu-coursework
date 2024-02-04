package com.kdu.smartHome.service;

import com.kdu.smartHome.dtos.DeviceDTO;
import com.kdu.smartHome.dtos.mappers.DeviceMapper;
import com.kdu.smartHome.dtos.response.AllInventoryResponseDTO;
import com.kdu.smartHome.entity.Device;
import com.kdu.smartHome.exceptions.custom.InvalidCredentials;
import com.kdu.smartHome.exceptions.custom.QueryEmptyException;
import com.kdu.smartHome.repository.DeviceRepository;
import com.kdu.smartHome.utils.CheckDeviceCredentials;
import com.kdu.smartHome.utils.DateTimeStampConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class DeviceService {
    private DeviceRepository deviceRepository;
    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public void addItemToInventory(DeviceDTO deviceDTO) {
        deviceDTO.setManufactureDateTime(DateTimeStampConverter.convertTimestampFormat(deviceDTO.getManufactureDateTime()));
        Device device = DeviceMapper.dtoToEntity(deviceDTO);
        log.info(device.toString());
        deviceRepository.save(device);
    }

    public void registerDevice(DeviceDTO dto) throws InvalidCredentials, QueryEmptyException {
        Optional<Device> res = deviceRepository.findById(Long.parseLong(dto.getKickstonId()));

        if(res.isPresent()) {
            if(CheckDeviceCredentials.checkCredentials(res.get(), dto)) {
                deviceRepository.updateRegisteredByKickstonIdEquals(true, Long.parseLong(dto.getKickstonId()));
            } else if (!Objects.equals(res.get().getDeviceUsername(), dto.getDeviceUsername()) && Objects.equals(res.get().getKickstonId().toString(), dto.getKickstonId())) {
                throw new QueryEmptyException("Device not found", new Exception());
            } else {
                throw new InvalidCredentials("Invalid Device Credentials", new Exception());
            }
        } else {
            throw new QueryEmptyException("Could not find device", new Exception());
        }
    }

    public AllInventoryResponseDTO getAllDevices() {
        AllInventoryResponseDTO res = new AllInventoryResponseDTO();
        List<Device> deviceList = deviceRepository.findAll();
        List<DeviceDTO> deviceDTOList = deviceList.stream().map(DeviceMapper::entityToDTO).toList();
        res.setInventory(deviceDTOList);
        return res;
    }
}
