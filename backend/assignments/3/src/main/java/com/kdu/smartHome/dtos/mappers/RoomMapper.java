package com.kdu.smartHome.dtos.mappers;

import com.kdu.smartHome.dtos.RoomDTO;
import com.kdu.smartHome.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * I am using the MapStruct library to generate the
 * mapper for Room DTO and Entities automatically. It injects all
 * of these at compile time hence we get nice type-safety while
 * writing our code.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoomMapper {
    @Mapping(target = "id", source = "room.id")
    @Mapping(target = "roomName", source = "room.name")
    RoomDTO entityToDTO(Room room);

    @Mapping(target = "name", source = "dto.roomName")
    Room dtoToEntity(RoomDTO dto);
}
