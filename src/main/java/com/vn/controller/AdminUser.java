package com.vn.controller;

import com.vn.dto.ReqRes;
import com.vn.entity.RealEstate;
import com.vn.repository.RealEstateRepository;
import com.vn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class AdminUser {
    @Autowired
    private RealEstateRepository realEstateRepository;
    @Autowired
    private UserService userService;
    @GetMapping("/public/res")
    public ResponseEntity<Object> getAllRE(){
        return ResponseEntity.ok(realEstateRepository.findAll());
    }
    @PostMapping("/admin/save")
    public ResponseEntity<Object> signUp(@RequestBody ReqRes reRequest){
        RealEstate realEstate = new RealEstate();
        realEstate.setTitle(reRequest.getName());
        return ResponseEntity.ok(realEstateRepository.save(realEstate));
    }
    @GetMapping("/admin/users")
    public ResponseEntity<Object> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
