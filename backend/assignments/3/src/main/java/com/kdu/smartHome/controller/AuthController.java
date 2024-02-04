package com.kdu.smartHome.controller;

import com.kdu.smartHome.dtos.UserDTO;
import com.kdu.smartHome.dtos.response.RegisterResponseDTO;
import com.kdu.smartHome.service.JwtService;
import com.kdu.smartHome.service.UserInfoService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Auth Controller contains the endpoints
 * which will be used for registration of the
 * user.
 */
@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private UserInfoService service;

    private JwtService jwtService;

    public AuthController(UserInfoService userInfoService,
            JwtService jwtService) {
        this.jwtService = jwtService;
        this.service = userInfoService;
    }

    /**
     * A test endpoint to see whether the server works fine
     * @return A simple string confirming the server is working as expected
     */
    @GetMapping("/test")
    public String welcome() {
        return "Testing.... The Server works fine...";
    }

    /**
     * The endpoint to register and create new users for the app.
     * @param userDTO Request Body that contains the details of the user.
     * @return JWT token generated for the particular user.
     */
    @PostMapping("/auth/register")
    public ResponseEntity<RegisterResponseDTO> addNewUser(@RequestBody UserDTO userDTO) {
        String message = service.addUser(userDTO);
        String token;
        token = jwtService.generateToken(userDTO.getUsername());
        return new ResponseEntity<>(new RegisterResponseDTO(message, token), HttpStatus.OK);
    }
}
