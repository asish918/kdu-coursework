package com.example.springbootapidemo.service;

import com.example.springbootapidemo.dto.AppUserDTO;
import com.example.springbootapidemo.model.AppUser;
import com.example.springbootapidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUserDTO> convertEntityListToDTOList(List<AppUser> appUsers) {
        List<AppUserDTO> newList = new ArrayList<>();
        appUsers.forEach(obj -> newList.add(new AppUserDTO(obj.getUserName(), obj.getEmail())));
        return newList;
    }
}
