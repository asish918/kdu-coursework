package asish.kdu.spring_jpa_exercise.service;

import asish.kdu.spring_jpa_exercise.entity.Product;
import asish.kdu.spring_jpa_exercise.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public void updateProduct(Integer id, Product product) {
        productRepository.updateNameAndDescriptionAndPriceAndStockByIdEquals(product.getName(), product.getDescription(), product.getPrice(), product.getStock(), id);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
