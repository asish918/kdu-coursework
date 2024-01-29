package com.example.springbootapidemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppUser {
    private String userName;
    private String password;
    private String email;
}
