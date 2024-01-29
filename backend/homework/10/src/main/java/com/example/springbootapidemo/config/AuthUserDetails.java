package com.example.springbootapidemo.config;

import com.example.springbootapidemo.model.AuthUser;
import com.example.springbootapidemo.repository.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AuthUserDetails implements UserDetailsService {

    private AuthRepository authRepository;
    @Autowired
    public AuthUserDetails(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authRepository.getAuthUserByName(username);
        String personUserName = null;
        String personPassword = null;
        List<GrantedAuthority> authorities = null;

        if(authUser == null){
            throw new UsernameNotFoundException("User details not found for user : " + username + ". Please register fist.");
        }else{
            personUserName = authUser.getUserName();
            personPassword = authUser.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(authUser.getRole()));
        }
        return new User(personUserName, personPassword, authorities);

    }
}
