package asish.kdu.spring_jpa_exercise.controller;

import asish.kdu.spring_jpa_exercise.entity.Product;
import asish.kdu.spring_jpa_exercise.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        log.info(product.toString());
        return new ResponseEntity<>("Product added", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @RequestParam String id) {
        productService.updateProduct(Integer.parseInt(id), product);
        return new ResponseEntity<>("Product updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam String id) {
        productService.deleteProduct(Integer.parseInt(id));
        return new ResponseEntity<>("Product is deleted", HttpStatus.OK);
    }
}
