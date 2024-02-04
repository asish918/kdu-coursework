package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD opeations on {@link com.kdu.smarthome.entity.Room RoomTable}
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
