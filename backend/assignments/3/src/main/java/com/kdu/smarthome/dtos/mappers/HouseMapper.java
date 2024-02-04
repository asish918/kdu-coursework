package com.kdu.smarthome.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kdu.smarthome.dtos.HouseDTO;
import com.kdu.smarthome.entity.House;

/**
 * I am using the MapStruct library to generate the
 * mapper for House DTO and Entities automatically. It injects all
 * of these at compile time hence we get nice type-safety while
 * writing our code.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface HouseMapper {
    @Mapping(target = "name", source = "house.name")
    @Mapping(target = "id", source = "house.id")
    @Mapping(target = "address", source = "house.address")
    HouseDTO entityToDTO(House house);

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "address", source = "dto.address")
    House dtoToEntity(HouseDTO dto);
}
