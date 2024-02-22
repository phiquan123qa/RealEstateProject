package com.vn.service;

import com.vn.entity.RealEstateDetailEntity;
import com.vn.entity.RealEstateEntity;
import com.vn.model.RealEstate;
import com.vn.repository.RealEstateDetailRepository;
import com.vn.repository.RealEstateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RealEstateServiceImpl implements RealEstateService {
    private final RealEstateRepository realEstateRepository;
    private final RealEstateDetailRepository realEstateDetailRepository;

    public RealEstateServiceImpl(RealEstateRepository realEstateRepository, RealEstateDetailRepository realEstateDetailRepository) {
        this.realEstateRepository = realEstateRepository;
        this.realEstateDetailRepository = realEstateDetailRepository;
    }

    @Override
    public RealEstate createRealEstate(RealEstate realEstate) {
        RealEstateEntity realEstateEntity = new RealEstateEntity();
        RealEstateDetailEntity realEstateDetail = new RealEstateDetailEntity();
        BeanUtils.copyProperties(realEstate.getRealEstateDetail(), realEstateDetail);
        BeanUtils.copyProperties(realEstate, realEstateEntity);
        realEstateEntity.setRealEstateDetail(realEstateDetail);
        realEstateRepository.save(realEstateEntity);
        return realEstate;
    }

    @Override
    public List<RealEstate> getAllRealEstates() {
        try {
            List<RealEstateEntity> realEstateEntities = realEstateRepository.findAll();
            return realEstateEntities
                    .stream()
                    .map(realEstateEntity -> new RealEstate(
                            realEstateEntity.getId(),
                            realEstateEntity.getTitle(),
                            realEstateEntity.getPrice(),
                            realEstateEntity.getLandArea(),
                            realEstateEntity.getMainImage(),
                            realEstateEntity.getRealEstateDetail(),
                            realEstateEntity.getOwner()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving real estates", e);
        }
    }

    @Override
    public RealEstate getRealEstateById(Long id) {
        Optional<RealEstateEntity> realEstateEntityOptional = realEstateRepository.findById(id);
        if (realEstateEntityOptional.isPresent()) {
            RealEstateEntity realEstateEntity = realEstateEntityOptional.get();
            RealEstate realEstate = new RealEstate();
            BeanUtils.copyProperties(realEstateEntity, realEstate);
            return realEstate;
        } else {
            throw new EntityNotFoundException("Real Estate not found");
        }
    }

    @Override
    public RealEstate updateRealEstate(Long id, RealEstate realEstate) {
        try {
            RealEstateEntity realEstateEntity = realEstateRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Real Estate not found with id" + id));
            RealEstateDetailEntity realEstateDetailEntity = realEstateEntity.getRealEstateDetail();
            if (realEstateDetailEntity == null) {
                throw new EntityNotFoundException("Real Estate not found for user with id" + id);
            }
            BeanUtils.copyProperties(realEstate, realEstateEntity, "id", "realEstateDetail");
            BeanUtils.copyProperties(realEstate.getRealEstateDetail(), realEstateEntity, "id");
            realEstateDetailRepository.save(realEstateDetailEntity);
            realEstateRepository.save(realEstateEntity);
            return realEstate;
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating real estate with id: " + id);
        }
    }

    @Override
    public List<RealEstate> getAllByTitle(String title) {
        try {
            List<RealEstate> realEstateEntities = realEstateRepository.findAllByTitleContaining(title);
            return realEstateEntities
                    .stream()
                    .map(realEstateEntity -> new RealEstate(realEstateEntity.getId(),
                            realEstateEntity.getTitle(),
                            realEstateEntity.getPrice(),
                            realEstateEntity.getLandArea(),
                            realEstateEntity.getMainImage(),
                            realEstateEntity.getRealEstateDetail(),
                            realEstateEntity.getOwner()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving real estates", e);
        }
    }
}
