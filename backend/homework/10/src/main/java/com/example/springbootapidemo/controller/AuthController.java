package com.example.springbootapidemo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    @GetMapping ("/login")
    public ResponseEntity<String> login(){
        log.info("Login Successful");
        return new ResponseEntity<>("Login Successful", HttpStatus.CREATED);
    }
}
