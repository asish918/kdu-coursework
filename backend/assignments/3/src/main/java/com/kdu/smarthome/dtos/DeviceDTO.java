package com.kdu.smarthome.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeviceDTO {
    @JsonProperty("kickston_id")
    private String kickstonId;
    @JsonProperty("device_username")
    private String deviceUsername;
    @JsonProperty("device_password")
    private String devicePassword;
    @JsonProperty("manufacture_date_time")
    private String manufactureDateTime;
    @JsonProperty("manufacture_factory_place")
    private String manufactureFactoryPlace;
}
