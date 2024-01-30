package com.vn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String avatar;
    private String role;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserDetailEntity userDetail;
    @OneToMany(mappedBy = "user")
    private List<UserLikeEntity> reList;
}
