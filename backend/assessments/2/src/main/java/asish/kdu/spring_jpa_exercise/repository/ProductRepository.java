package asish.kdu.spring_jpa_exercise.repository;

import asish.kdu.spring_jpa_exercise.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Transactional
    @Modifying
    @Query("""
            update Product p set p.name = :name, p.description = :description, p.price = :price, p.stock = :stock
            where p.id = :id""")
    void updateNameAndDescriptionAndPriceAndStockByIdEquals(@Param("name") String name, @Param("description") String description, @Param("price") Double price, @Param("stock") Integer stock, @Param("id") Integer id);

    Product findByIdEquals(Integer id);
}