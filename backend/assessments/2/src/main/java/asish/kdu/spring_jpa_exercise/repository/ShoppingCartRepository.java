package asish.kdu.spring_jpa_exercise.repository;

import asish.kdu.spring_jpa_exercise.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    @Query("select s from ShoppingCart s where s.userInfo.id = :id")
    ShoppingCart findByUserInfo_IdEquals(@Param("id") Integer id);

}