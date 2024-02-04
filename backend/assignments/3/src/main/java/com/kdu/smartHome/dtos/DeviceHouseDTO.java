package com.kdu.smartHome.dtos;

import lombok.Data;

/**
 * ● houseId (Type: String) - The identifier of the house to which the device will be added.
 * ● roomId (Type: String) - The identifier of the room in the house where the device will be placed.
 * ● kickstonId (Type: String) - The identifier of the device to be added.
 */
@Data
public class DeviceHouseDTO {
    private String houseId;
    private String roomId;
    private String kickstonId;
}
