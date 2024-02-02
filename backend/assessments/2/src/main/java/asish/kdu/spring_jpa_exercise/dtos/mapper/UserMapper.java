package asish.kdu.spring_jpa_exercise.dtos.mapper;

import asish.kdu.spring_jpa_exercise.dtos.UserDTO;
import asish.kdu.spring_jpa_exercise.entity.UserInfo;

public class UserMapper {
    private UserMapper() {

    }

    public static UserInfo dtoToEntity(UserDTO userDTO) {
        UserInfo userInfo = new UserInfo();

        userInfo.setName(userDTO.getName());
        userInfo.setPassword(userDTO.getPassword());
        userInfo.setEmail(userDTO.getEmail());
        userInfo.setRoles(userDTO.getRoles());

        return userInfo;
    }

    public static UserDTO entityToDto(UserInfo userInfo) {
        UserDTO userDTO = new UserDTO();

        userDTO.setName(userInfo.getName());
        userDTO.setEmail(userInfo.getEmail());
        userDTO.setRoles(userInfo.getRoles());

        return userDTO;
    }
}
