package com.kdu.smartHome.service;

import com.kdu.smartHome.dtos.UserDTO;
import com.kdu.smartHome.dtos.mappers.UserMapper;
import com.kdu.smartHome.entity.UserInfo;
import com.kdu.smartHome.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service layer for {@link com.kdu.smartHome.entity.UserInfo UserInfoEntity}
 */
@Service
@Slf4j
public class UserInfoService implements UserDetailsService {
    private UserInfoRepository repository;

    private PasswordEncoder encoder;

    private UserMapper userMapper;

    @Autowired
    public UserInfoService(UserInfoRepository repository, PasswordEncoder encoder, UserMapper userMapper) {
        this.repository = repository;
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    public UserInfoService() {}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByUsername(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserDTO userDto) {
        UserInfo userInfo = userMapper.dtoToEntity(userDto);

        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfo.setRoles("ROLE_USER");

        repository.save(userInfo);

        return "User Added Successfully";
    }
}
