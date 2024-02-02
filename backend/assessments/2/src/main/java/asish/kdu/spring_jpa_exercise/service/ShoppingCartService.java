package asish.kdu.spring_jpa_exercise.service;

import asish.kdu.spring_jpa_exercise.entity.Product;
import asish.kdu.spring_jpa_exercise.entity.ShoppingCart;
import asish.kdu.spring_jpa_exercise.repository.ProductRepository;
import asish.kdu.spring_jpa_exercise.repository.ShoppingCartRepository;
import asish.kdu.spring_jpa_exercise.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;

    private UserInfoRepository userInfoRepository;
    private ProductRepository productRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, UserInfoRepository userInfoRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userInfoRepository = userInfoRepository;
        this.productRepository = productRepository;
    }
    public void addNewCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }
    public void addProductToCart(String userId, String productId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserInfo_IdEquals(Integer.parseInt(userId));
        Product product = productRepository.findByIdEquals(Integer.parseInt(productId));
        shoppingCart.getProductList().add(product);
        shoppingCartRepository.save(shoppingCart);
    }

    public void deleteProductFromCart(String userId, String productId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserInfo_IdEquals(Integer.parseInt(userId));
        Product product = productRepository.findByIdEquals(Integer.parseInt(productId));
        log.info(product.toString());
//        shoppingCart.getProductList().remove(product);
//        shoppingCartRepository.save(shoppingCart);
    }
}
