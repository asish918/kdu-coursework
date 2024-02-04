package com.kdu.smartHome.dtos.mappers;

import com.kdu.smartHome.dtos.RoomDTO;
import com.kdu.smartHome.dtos.UserDTO;
import com.kdu.smartHome.entity.Room;
import com.kdu.smartHome.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoomMapper {
    RoomDTO entityToDTO(Room room);

    @Mapping(target = "name", source = "dto.roomName")
    Room dtoToEntity(RoomDTO dto);
}
