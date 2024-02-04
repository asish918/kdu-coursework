package com.kdu.smartHome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kdu.smartHome.entity.House;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for CRUD opeations on {@link com.kdu.smartHome.entity.House HoueTable}
 */
@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    /**
     * Custom query for updating the address of the house
     * @param address New Address to be set
     * @param id Id of the house
     */
    @Transactional
    @Modifying
    @Query("update House h set h.address = ?1 where h.id = ?2")
    int updateAddressByIdEquals(String address, Long id);

}
