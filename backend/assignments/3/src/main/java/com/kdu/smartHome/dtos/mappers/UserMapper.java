package com.kdu.smartHome.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kdu.smartHome.dtos.UserDTO;
import com.kdu.smartHome.entity.UserInfo;

/**
 * I am using the MapStruct library to generate the
 * mapper for User DTO and Entities automatically. It injects all
 * of these at compile time hence we get nice type-safety while
 * writing our code.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "emailId", source = "user.email")
    @Mapping(target = "username", source = "user.username")
    UserDTO entityToDTO(UserInfo user);

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "username", source = "dto.username")
    @Mapping(target = "password", source = "dto.password")
    @Mapping(target = "email", source = "dto.emailId")
    UserInfo dtoToEntity(UserDTO dto);
}