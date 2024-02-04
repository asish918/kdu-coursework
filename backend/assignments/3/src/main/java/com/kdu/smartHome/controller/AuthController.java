package com.kdu.smartHome.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdu.smartHome.dtos.AuthRequest;
import com.kdu.smartHome.dtos.UserDTO;
import com.kdu.smartHome.dtos.response.RegisterResponseDTO;
import com.kdu.smartHome.entity.UserInfo;
import com.kdu.smartHome.service.JwtService;
import com.kdu.smartHome.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private UserInfoService service;

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager, UserInfoService userInfoService,
            JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.service = userInfoService;
    }

    @GetMapping("/test")
    public String welcome() {
        return "Testing.... The Server works fine...";
    }

    @PostMapping("/auth/register")
    public ResponseEntity<RegisterResponseDTO> addNewUser(@RequestBody UserDTO userDTO) {
        String message = service.addUser(userDTO);
        String token;
        token = jwtService.generateToken(userDTO.getUsername());
        return new ResponseEntity<>(new RegisterResponseDTO(message, token), HttpStatus.OK);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
