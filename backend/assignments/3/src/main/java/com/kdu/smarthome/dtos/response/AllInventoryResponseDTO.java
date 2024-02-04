package com.kdu.smarthome.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AllInventoryResponseDTO {
    @JsonProperty("inventory")
    private String inventory;
}
