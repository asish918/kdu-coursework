package com.kdu.smarthome.utils;

import com.kdu.smarthome.dtos.DeviceDTO;
import com.kdu.smarthome.entity.Device;

import java.util.Objects;

/**
 * Singleton Utility function to verify device credentials
 */
public class CheckDeviceCredentials {
    public static boolean checkCredentials(Device device, DeviceDTO dto) {
        return Objects.equals(device.getDeviceUsername(), dto.getDeviceUsername()) && Objects.equals(dto.getDevicePassword(), device.getDevicePassword());
    }
}
