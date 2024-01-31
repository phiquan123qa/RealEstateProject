package com.vn.repository;

import com.vn.entity.UserDetailEntity;
import com.vn.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Long> {
    @Query("SELECT u FROM userDetail u WHERE u.email = :searchTerm")
    UserDetailEntity findByEmail(@Param("searchTerm") String searchTerm);
}
