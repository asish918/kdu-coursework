package com.kdu.smarthome.service;

import com.kdu.smarthome.dtos.DeviceDTO;
import com.kdu.smarthome.dtos.mappers.DeviceMapper;
import com.kdu.smarthome.dtos.response.AllInventoryResponseDTO;
import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.exceptions.custom.InvalidCredentials;
import com.kdu.smarthome.exceptions.custom.QueryEmptyException;
import com.kdu.smarthome.repository.DeviceRepository;
import com.kdu.smarthome.utils.CheckDeviceCredentials;
import com.kdu.smarthome.utils.DateTimeStampConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service layer for {@link com.kdu.smarthome.entity.Device DeviceEntity}
 */
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
        res.setInventory(deviceDTOList.toString());
        return res;
    }
}
