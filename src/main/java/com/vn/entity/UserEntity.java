package com.vn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "fk_ud_id")
    @JsonBackReference
    private UserDetailEntity userDetail;
    @OneToMany(cascade = CascadeType.ALL)
//@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "fk_ul_id", referencedColumnName = "id")
    private List<UserLikeEntity> reList;
}
