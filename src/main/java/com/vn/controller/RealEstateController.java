package com.vn.controller;

import com.vn.entity.RealEstate;
import com.vn.service.RealEstateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/re")
public class RealEstateController {
    @Autowired
    private final RealEstateService realEstateService;

    public RealEstateController(RealEstateService realEstateService) {
        this.realEstateService = realEstateService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createRealEstate(@RequestBody RealEstate realEstate) {
        try {
            RealEstate createdRealEstate = realEstateService.createRealEstate(realEstate);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRealEstate);
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Real Estate with the same already exists");
        }
    }

    @GetMapping("/res")
    public ResponseEntity<List<RealEstate>> getAllRealEstates() {
        List<RealEstate> realEstates = realEstateService.getAllRealEstates();
        if (realEstates.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(realEstates);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRealEstateById(@PathVariable("id") Long id) {
        try {
            RealEstate realEstate = realEstateService.getRealEstateById(id);
            return ResponseEntity.ok(realEstate);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Real Estate not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<List<RealEstate>> getRealEstateByTitle(@PathVariable("searchTerm") String searchTerm) {
        List<RealEstate> realEstates = realEstateService.getAllByTitle(searchTerm);
        if (realEstates.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(realEstates);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRealEstate(@PathVariable("id") Long id, @RequestBody RealEstate realEstate) {
        try {
            RealEstate updatedRealEstate = realEstateService.updateRealEstate(id, realEstate);
            return ResponseEntity.ok(updatedRealEstate);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Real Estate not found with id " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating real estate with id " + id);
        }
    }
}
