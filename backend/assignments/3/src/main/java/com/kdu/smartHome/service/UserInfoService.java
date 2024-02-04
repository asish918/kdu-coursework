package com.kdu.smartHome.service;

import com.kdu.smartHome.dtos.UserDTO;
import com.kdu.smartHome.dtos.mappers.UserMapper;
import com.kdu.smartHome.dtos.mappers.UserMapperImpl;
import com.kdu.smartHome.entity.UserInfo;
import com.kdu.smartHome.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserMapper userMapper;

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
