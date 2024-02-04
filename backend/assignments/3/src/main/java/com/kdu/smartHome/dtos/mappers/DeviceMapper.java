package com.kdu.smartHome.dtos.mappers;

import com.kdu.smartHome.dtos.DeviceDTO;
import com.kdu.smartHome.entity.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.sql.Time;
import java.sql.Timestamp;


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