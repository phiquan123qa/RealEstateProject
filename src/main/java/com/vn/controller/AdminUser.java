package com.vn.controller;

import com.vn.dto.ReqRes;
import com.vn.entity.RealEstate;
import com.vn.repository.RealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class AdminUser {
    @Autowired
    private RealEstateRepository realEstateRepository;
    @GetMapping("/public/res")
    public ResponseEntity<Object> getAllRE(){
        return ResponseEntity.ok(realEstateRepository.findAll());
    }
    @PostMapping("/admin/savere")
    public ResponseEntity<Object> signUp(@RequestBody ReqRes reRequest){
        RealEstate realEstate = new RealEstate();
        realEstate.setTitle(reRequest.getName());
        return ResponseEntity.ok(realEstateRepository.save(realEstate));
    }
}
