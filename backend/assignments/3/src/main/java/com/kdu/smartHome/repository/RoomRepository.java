package com.kdu.smartHome.repository;

import com.kdu.smartHome.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD opeations on {@link com.kdu.smartHome.entity.Room RoomTable}
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
