package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for CRUD opeations on {@link com.kdu.smarthome.entity.Device DeviceTable}
 */
public interface DeviceRepository extends JpaRepository<Device, Long> {
    /**
     * Custom query to set the registered field of Device to true.
     * @param registered Boolean Value
     * @param kickstonId Device Id
     */
    @Transactional
    @Modifying
    @Query("update Device d set d.registered = ?1 where d.kickstonId = ?2")
    int updateRegisteredByKickstonIdEquals(Boolean registered, Long kickstonId);
}
