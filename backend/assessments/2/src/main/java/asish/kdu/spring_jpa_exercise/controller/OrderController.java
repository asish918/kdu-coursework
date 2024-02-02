package asish.kdu.spring_jpa_exercise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class OrderController {
    @PostMapping("/")
    public ResponseEntity<String> placeOrder(@RequestParam String id) {

        return new ResponseEntity<>("Order Placed Successfully!", HttpStatus.OK);
    }
}
