package asish.kdu.sring_jdbc_homework.controller;

import asish.kdu.sring_jdbc_homework.dtos.UsersDTO;
import asish.kdu.sring_jdbc_homework.exceptions.custom.NoSQLDataFound;
import asish.kdu.sring_jdbc_homework.models.Users;
import asish.kdu.sring_jdbc_homework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UsersDTO usersDTO) {
        userService.addUser(usersDTO);
        return new ResponseEntity<>("User added successfully!!", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers(@RequestParam String tenantId) throws NoSQLDataFound {
        List<Users> users = userService.getUsers(tenantId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
