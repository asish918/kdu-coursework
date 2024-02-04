package com.kdu.smartHome.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kdu.smartHome.dtos.DeviceDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AllInventoryResponseDTO {
    @JsonProperty("inventory")
    private String inventory;
}
