package com.kdu.smartHome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kdu.smartHome.entity.House;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    @Transactional
    @Modifying
    @Query("update House h set h.address = ?1 where h.id = ?2")
    int updateAddressByIdEquals(String address, Long id);

}
