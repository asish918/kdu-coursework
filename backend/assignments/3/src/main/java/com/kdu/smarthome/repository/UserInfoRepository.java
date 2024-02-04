package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for CRUD opeations on {@link com.kdu.smarthome.entity.UserInfo UserTable}
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	Optional<UserInfo> findByName(String username);

	Optional<UserInfo> findByUsername(String username);
}
