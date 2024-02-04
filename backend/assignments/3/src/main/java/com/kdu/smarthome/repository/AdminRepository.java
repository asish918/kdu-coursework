package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.maps.AdminTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Repository for CRUD opeations on {@link com.kdu.smarthome.entity.maps.AdminTable AdminTable}
 */
public interface AdminRepository extends JpaRepository<AdminTable, House> {
    @Query("select a from AdminTable a where a.house = ?1")
    Optional<AdminTable> findByHouseEquals(House house);
}
