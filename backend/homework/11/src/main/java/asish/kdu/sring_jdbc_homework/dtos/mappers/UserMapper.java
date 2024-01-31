package asish.kdu.sring_jdbc_homework.dtos.mappers;

import asish.kdu.sring_jdbc_homework.dtos.UsersDTO;
import asish.kdu.sring_jdbc_homework.models.Users;

public class UserMapper {
    private UserMapper() {

    }

    public static Users dtoToEntity(UsersDTO usersDTO) {
        Users users = new Users();

        users.setUsername(usersDTO.getUsername());
        users.setLoggedin(Integer.valueOf(usersDTO.getLoggedin()));
        users.setTimezone(usersDTO.getTimezone());
        users.setTenantId(usersDTO.getTenantId());

        return users;
    }

    public static UsersDTO entityToDto(Users users) {
        return new UsersDTO(
                users.getId(),
                users.getUsername(),
                Short.valueOf(users.getLoggedin().toString()),
                users.getTimezone(),
                users.getTenantId()
        );
    }
}
