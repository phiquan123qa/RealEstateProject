package com.vn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "realEstate")
@Data
public class RealEstateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double price;
    private Integer landArea;
    @Lob
    @Column(name = "mainImage", columnDefinition = "BLOB")
    private byte[] mainImage;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_red_id")
    @JsonBackReference
    private RealEstateDetailEntity realEstateDetail;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_re_id", referencedColumnName = "id")
    private List<UserLikeEntity> reList;
}
