package com.vn.repository;

import com.vn.entity.RealEstateEntity;
import com.vn.model.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstateEntity, Long> {
    List<RealEstate> getAllByTitleContaining(String title);

    List<RealEstate> findAllByTitleContaining(String title);


}
