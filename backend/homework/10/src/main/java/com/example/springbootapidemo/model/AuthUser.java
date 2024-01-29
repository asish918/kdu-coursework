package com.example.springbootapidemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class AuthUser {
    private String name;
    private String userName;
    private String password;
    private String role;
}
