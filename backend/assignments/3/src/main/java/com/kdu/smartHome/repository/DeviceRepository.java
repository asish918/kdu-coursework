package com.kdu.smartHome.repository;

import com.kdu.smartHome.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    @Transactional
    @Modifying
    @Query("update Device d set d.registered = ?1 where d.kickstonId = ?2")
    int updateRegisteredByKickstonIdEquals(Boolean registered, Long kickstonId);
}
