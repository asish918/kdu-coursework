package com.example.springbootapidemo.dto;

import com.example.springbootapidemo.model.AppUser;

public class AppUserMapper {
    private AppUserMapper() {

    }
    public static AppUserDTO entityToDTO(AppUser appUser) {
        return new AppUserDTO(appUser.getUserName(), appUser.getEmail());
    }
}
