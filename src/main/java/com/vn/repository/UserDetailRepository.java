package com.vn.repository;

import com.vn.entity.UserDetailEntity;
import com.vn.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Long> {
}
