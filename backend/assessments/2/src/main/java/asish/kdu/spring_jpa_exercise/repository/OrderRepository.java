package asish.kdu.spring_jpa_exercise.repository;

import asish.kdu.spring_jpa_exercise.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
}