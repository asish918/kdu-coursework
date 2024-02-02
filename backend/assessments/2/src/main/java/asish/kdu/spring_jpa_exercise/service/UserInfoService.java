package asish.kdu.spring_jpa_exercise.service;

import asish.kdu.spring_jpa_exercise.dtos.AddressDTO;
import asish.kdu.spring_jpa_exercise.dtos.UserDTO;
import asish.kdu.spring_jpa_exercise.dtos.mapper.AddressMapper;
import asish.kdu.spring_jpa_exercise.dtos.mapper.UserMapper;
import asish.kdu.spring_jpa_exercise.entity.Address;
import asish.kdu.spring_jpa_exercise.entity.ShoppingCart;
import asish.kdu.spring_jpa_exercise.entity.UserInfo;
import asish.kdu.spring_jpa_exercise.repository.AddressRepository;
import asish.kdu.spring_jpa_exercise.repository.ShoppingCartRepository;
import asish.kdu.spring_jpa_exercise.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
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
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByName(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserDTO userDto) {
        UserInfo userInfo = UserMapper.dtoToEntity(userDto);

        userInfo.setPassword(encoder.encode(userInfo.getPassword()));

        repository.save(userInfo);

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setUserInfo(userInfo);

        userInfo.setCart(shoppingCart);

		repository.save(userInfo);

        return "User Added Successfully";
    }

    public void addUserAddress(Integer id, AddressDTO addressDTO) {
        Optional<UserInfo> user = repository.findById(id);

        Address address = AddressMapper.dtoToEntity(addressDTO);

        addressRepository.save(address);
    }

    public UserDTO getDetails(Integer id) {
        UserInfo userInfo = repository.findById(id).get();
		return UserMapper.entityToDto(userInfo);
    }
} 
