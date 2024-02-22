package com.vn.repository;

import com.vn.entity.RealEstateDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealEstateDetailRepository extends JpaRepository<RealEstateDetailEntity, Long> {
}
