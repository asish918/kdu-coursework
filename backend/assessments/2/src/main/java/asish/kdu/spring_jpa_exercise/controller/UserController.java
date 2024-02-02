package asish.kdu.spring_jpa_exercise.controller;

import asish.kdu.spring_jpa_exercise.dtos.AddressDTO;
import asish.kdu.spring_jpa_exercise.dtos.UserDTO;
import asish.kdu.spring_jpa_exercise.service.ShoppingCartService;
import asish.kdu.spring_jpa_exercise.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
    private UserInfoService userInfoService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public UserController(UserInfoService userInfoService, ShoppingCartService shoppingCartService) {
        this.userInfoService = userInfoService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/address")
    public ResponseEntity<String> addUserAddress(@RequestParam String id, @RequestBody AddressDTO addressDTO) {
        log.info(addressDTO.toString());
        userInfoService.addUserAddress(Integer.parseInt(id), addressDTO);
        return new ResponseEntity<>("Address added to user!", HttpStatus.OK);
    }

    @GetMapping("/details")
    public ResponseEntity<UserDTO> getUserDetails(String id) {
        UserDTO userInfo = userInfoService.getDetails(Integer.parseInt(id));
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<String> addToCart(@RequestParam String userId, @RequestParam String productId) {
        shoppingCartService.addProductToCart(userId, productId);
        return new ResponseEntity<>("Product added to Cart!", HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<String> deleteFromCart(@RequestParam String userId, @RequestParam String productId) {
        shoppingCartService.deleteProductFromCart(userId, productId);
        return new ResponseEntity<>("Product deleted from Cart!", HttpStatus.OK);
    }
}
