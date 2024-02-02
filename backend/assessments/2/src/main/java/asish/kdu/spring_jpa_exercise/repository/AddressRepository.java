package asish.kdu.spring_jpa_exercise.repository;

import asish.kdu.spring_jpa_exercise.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}