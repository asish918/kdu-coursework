package com.kdu.smarthome.controller;

import com.kdu.smarthome.dtos.UserDTO;
import com.kdu.smarthome.dtos.response.RegisterResponseDTO;
import com.kdu.smarthome.service.JwtService;
import com.kdu.smarthome.service.UserInfoService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
     *
     * @return A simple string confirming the server is working as expected
     */
    @GetMapping("/test")
    public String welcome() {
        return "Testing.... The Server works fine...";
    }

    /**
     * The endpoint to register and create new users for the app.
     *
     * @param userDTO Request Body that contains the details of the user.
     * @return JWT token generated for the particular user.
     */
    @Operation(summary = "Register a new user", description = "Returns a token once user is registered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registered"),
    }
    )
    @PostMapping("/auth/register")
    public ResponseEntity<RegisterResponseDTO> addNewUser(@RequestBody UserDTO userDTO) {
        String message = service.addUser(userDTO);
        String token;
        token = jwtService.generateToken(userDTO.getUsername());
        return new ResponseEntity<>(new RegisterResponseDTO(message, token), HttpStatus.OK);
    }
}
