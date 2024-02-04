package com.kdu.smartHome.repository;

import com.kdu.smartHome.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	Optional<UserInfo> findByName(String username);

	Optional<UserInfo> findByUsername(String username);
}
