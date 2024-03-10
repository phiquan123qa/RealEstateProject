package com.vn.controller;

import com.vn.dto.ReqRes;
import com.vn.entity.RealEstate;
import com.vn.entity.User;
import com.vn.repository.RealEstateRepository;
import com.vn.repository.UserRepository;
import com.vn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminUser {
    @Autowired
    private RealEstateRepository realEstateRepository;
    @Autowired
    private UserRepository userRepository;
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
    public ResponseEntity<Object> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(users);
        }
    }
    @GetMapping("/admin/hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Heloooooooooooo");
    }
}
