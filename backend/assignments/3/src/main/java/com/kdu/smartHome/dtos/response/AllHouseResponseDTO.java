package com.kdu.smartHome.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kdu.smartHome.dtos.HouseDTO;
import com.kdu.smartHome.entity.House;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AllHouseResponseDTO {

    @JsonProperty("houses")
    private String houses;
}
