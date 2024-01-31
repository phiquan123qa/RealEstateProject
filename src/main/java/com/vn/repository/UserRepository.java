package com.vn.repository;

import com.vn.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM user u JOIN u.userDetail ud WHERE ud.email = :searchTerm")
    UserEntity findByEmail(@Param("searchTerm") String searchTerm);
    @Query("SELECT u FROM user u JOIN u.userDetail ud WHERE ud.phoneNumber = :searchTerm")
    UserEntity findByPhoneNumber(@Param("searchTerm") String searchTerm);
}
