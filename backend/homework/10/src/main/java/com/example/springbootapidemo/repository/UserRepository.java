package com.example.springbootapidemo.repository;

import com.example.springbootapidemo.exception.custom.UserNotFoundException;
import com.example.springbootapidemo.model.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepository {
    private List<AppUser> appUsers;

    @PostConstruct
    public void repoInit() {
        this.appUsers = new ArrayList<>();
    }

    public void addAppUser(AppUser appUser) {
        appUsers.add(appUser);
    }

    public AppUser findAppUserByName(String name) throws UserNotFoundException {
        for(AppUser appUser : appUsers) {
            if(Objects.equals(appUser.getUserName(), name)) {
                return appUser;
            }
        }

        throw new UserNotFoundException("User not found in the database", new Exception());

    }

    public List<AppUser> getAllUsers() {
        return appUsers;
    }
}
