package asish.kdu.sring_jdbc_homework.services;

import asish.kdu.sring_jdbc_homework.dtos.UsersDTO;
import asish.kdu.sring_jdbc_homework.dtos.mappers.UserMapper;
import asish.kdu.sring_jdbc_homework.exceptions.custom.NoSQLDataFound;
import asish.kdu.sring_jdbc_homework.models.Users;
import asish.kdu.sring_jdbc_homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UsersDTO usersDTO) {
        Users users = UserMapper.dtoToEntity(usersDTO);
        userRepository.createUser(users);
    }

    public void updateUser(UsersDTO usersDTO) {
        Users users = UserMapper.dtoToEntity(usersDTO);
        users.setId(usersDTO.getId());
        userRepository.updateUser(users);
    }

    public List<Users> getUsers(String tenantId) throws NoSQLDataFound {
        List<Users> usersList = userRepository.getUsers(tenantId);
        if(usersList.isEmpty())
            throw new NoSQLDataFound("No entry present in the database", new Exception());

        return usersList;
    }
}
