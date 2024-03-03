package com.vn.service;

import com.vn.entity.RealEstate;
import com.vn.repository.RealEstateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RealEstateService {
    @Autowired
    private final RealEstateRepository realEstateRepository;

    public RealEstateService(RealEstateRepository realEstateRepository) {
        this.realEstateRepository = realEstateRepository;
    }

    public RealEstate createRealEstate(RealEstate realEstate) {
        RealEstate realEstateEntity = new RealEstate();
        BeanUtils.copyProperties(realEstate, realEstateEntity);
        realEstateRepository.save(realEstateEntity);
        return realEstate;
    }

    public List<RealEstate> getAllRealEstates() {
        try {
            List<RealEstate> realEstateEntities = realEstateRepository.findAll();
            return realEstateEntities;
//                    .stream()
//                    .map(realEstateEntity -> new RealEstate(
//                            realEstateEntity.getId(),
//                            realEstateEntity.getTitle(),
//                            realEstateEntity.getPrice(),
//                            realEstateEntity.getLandArea(),
//                            realEstateEntity.getMainImage(),
//                            realEstateEntity.getCityRe(),
//                            realEstateEntity.getDistrictRe(),
//                            realEstateEntity.getWardRe(),
//                            realEstateEntity.getAddress(),
//                            realEstateEntity.getDescription(),
//                            realEstateEntity.getDateStart(),
//                            realEstateEntity.getDateEnd(),
//                            realEstateEntity.getType(),
//                            realEstateEntity.getStatusRe()
//                            ))
//                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving real estates", e);
        }
    }

    public RealEstate getRealEstateById(Long id) {
        Optional<RealEstate> realEstateEntityOptional = realEstateRepository.findById(id);
        if (realEstateEntityOptional.isPresent()) {
            RealEstate realEstateEntity = realEstateEntityOptional.get();
            RealEstate realEstate = new RealEstate();
            BeanUtils.copyProperties(realEstateEntity, realEstate);
            return realEstate;
        } else {
            throw new EntityNotFoundException("Real Estate not found");
        }
    }

    public RealEstate updateRealEstate(Long id, RealEstate realEstate) {
        try {
            RealEstate realEstateEntity = realEstateRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Real Estate not found with id" + id));

            BeanUtils.copyProperties(realEstate, realEstateEntity, "id", "realEstateDetail");
            realEstateRepository.save(realEstateEntity);
            return realEstate;
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating real estate with id: " + id);
        }
    }

    public List<RealEstate> getAllByTitle(String title) {
        try {
            List<RealEstate> realEstateEntities = realEstateRepository.findAllByTitleContaining(title);
            return realEstateEntities;
//                    .stream()
//                    .map(realEstateEntity -> new RealEstate(realEstateEntity.getId(),
//                            realEstateEntity.getTitle(),
//                            realEstateEntity.getPrice(),
//                            realEstateEntity.getLandArea(),
//                            realEstateEntity.getMainImage()
//                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving real estates", e);
        }
    }
}
