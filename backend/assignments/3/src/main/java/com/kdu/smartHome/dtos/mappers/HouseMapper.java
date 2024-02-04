package com.kdu.smartHome.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kdu.smartHome.dtos.HouseDTO;
import com.kdu.smartHome.entity.House;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface HouseMapper {
    @Mapping(target = "houseName", source = "house.name")
    @Mapping(target = "address", source = "house.address")
    HouseDTO entityToDTO(House house);

    @Mapping(target = "name", source = "dto.houseName")
    @Mapping(target = "address", source = "dto.address")
    House dtoToEntity(HouseDTO dto);
}
