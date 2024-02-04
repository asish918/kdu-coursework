package com.kdu.smartHome.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kdu.smartHome.dtos.UserDTO;
import com.kdu.smartHome.entity.UserInfo;

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