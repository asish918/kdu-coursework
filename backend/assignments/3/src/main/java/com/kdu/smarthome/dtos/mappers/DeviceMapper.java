package com.kdu.smarthome.dtos.mappers;

import com.kdu.smarthome.dtos.DeviceDTO;
import com.kdu.smarthome.entity.Device;
import java.sql.Timestamp;

/**
 * Mapper to convert DTO and Entity of the Device
 */
public class DeviceMapper {
    private DeviceMapper() {

    }

    public static DeviceDTO entityToDTO(Device device) {
        DeviceDTO deviceDTO = new DeviceDTO();

        deviceDTO.setKickstonId(device.getKickstonId().toString());
        deviceDTO.setDeviceUsername(device.getDeviceUsername());
        deviceDTO.setDevicePassword(device.getDevicePassword());
        deviceDTO.setManufactureDateTime(device.getManufactureDateTime().toString());
        deviceDTO.setManufactureFactoryPlace(device.getManufactureFactoryPlace());

        return deviceDTO;
    }

    public static Device dtoToEntity(DeviceDTO dto) {
        Device device = new Device();

        device.setKickstonId(Long.parseLong(dto.getKickstonId()));
        device.setDeviceUsername(dto.getDeviceUsername());
        device.setDevicePassword(dto.getDevicePassword());
        device.setManufactureDateTime(Timestamp.valueOf(dto.getManufactureDateTime()));
        device.setManufactureFactoryPlace(dto.getManufactureFactoryPlace());

        return device;
    }

}