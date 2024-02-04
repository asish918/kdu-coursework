package com.kdu.smartHome.utils;

import com.kdu.smartHome.dtos.DeviceDTO;
import com.kdu.smartHome.entity.Device;

import java.util.Objects;

public class CheckDeviceCredentials {
    public static boolean checkCredentials(Device device, DeviceDTO dto) {
        return Objects.equals(device.getDeviceUsername(), dto.getDeviceUsername()) && Objects.equals(dto.getDevicePassword(), device.getDevicePassword());
    }
}
