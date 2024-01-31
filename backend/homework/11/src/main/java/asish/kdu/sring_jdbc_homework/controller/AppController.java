package asish.kdu.sring_jdbc_homework.controller;

import asish.kdu.sring_jdbc_homework.dtos.CompleteDTO;
import asish.kdu.sring_jdbc_homework.dtos.UsersDTO;
import asish.kdu.sring_jdbc_homework.services.AppService;
import asish.kdu.sring_jdbc_homework.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AppController {
    private final AppService appService;
    private final UserService userService;

    @Autowired
    public AppController(AppService appService, UserService userService) {
        this.appService = appService;
        this.userService = userService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        log.info("TEST GET Request successful");
        return new ResponseEntity<>("Server working. Test successful....", HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<String> createAll(@RequestBody CompleteDTO completeDTO) {
        appService.addAllEntries(completeDTO);
        return new ResponseEntity<>("Created all entries", HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<String> updateUser(@RequestBody UsersDTO usersDTO) {
        userService.updateUser(usersDTO);
        return new ResponseEntity<>("Updated User successfully!!", HttpStatus.OK);
    }
}
