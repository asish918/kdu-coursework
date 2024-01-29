package com.example.springbootapidemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppUserDTO {
    private String userName;
    private String email;
}
