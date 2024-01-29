package com.example.springbootapidemo.controller;

import com.example.springbootapidemo.dto.AppUserDTO;
import com.example.springbootapidemo.dto.AppUserMapper;
import com.example.springbootapidemo.exception.custom.UserNotFoundException;
import com.example.springbootapidemo.model.AppUser;
import com.example.springbootapidemo.repository.UserRepository;
import com.example.springbootapidemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {
    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<AppUserDTO>> getAllUsers() {
        List<AppUser> appUsers = userRepository.getAllUsers();
        List<AppUserDTO> convertedList = userService.convertEntityListToDTOList(appUsers);
        log.info("All users sent successfully");
        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @GetMapping("/username")
    @SuppressWarnings("java:S1130")
    public ResponseEntity<AppUserDTO> getUserByName(@RequestParam String name) throws UserNotFoundException {
        AppUser appUser = userRepository.findAppUserByName(name);
        AppUserDTO appUserDTO = AppUserMapper.entityToDTO(appUser);
        log.info("User sent successfully");
        return new ResponseEntity<>(appUserDTO, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody AppUser appUser) {
        userRepository.addAppUser(appUser);
        log.info("User added successfully");
        return new ResponseEntity<>("User Added to Database successfully", HttpStatus.OK);
    }
}
