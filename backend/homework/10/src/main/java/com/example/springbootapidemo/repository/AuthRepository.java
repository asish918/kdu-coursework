package com.example.springbootapidemo.repository;

import com.example.springbootapidemo.model.AuthUser;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class AuthRepository {
    private List<AuthUser> authUsers;

    @PostConstruct
    public void repoInit() {
        this.authUsers = new ArrayList<>();
    }

    public void addAuthUser(AuthUser authUser) {
        authUsers.add(authUser);
    }

    public AuthUser getAuthUserByName(String name) {
        for(AuthUser authUser : authUsers) {
            if(Objects.equals(authUser.getUserName(), name))
                return authUser;
        }

        return null;
    }
}
