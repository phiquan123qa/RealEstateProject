package com.vn.entity;

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
    @OneToOne(mappedBy = "realEstate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private RealEstateDetailEntity realEstateDetail;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;
    @OneToMany(mappedBy = "realEstate")
    private List<UserLikeEntity> reList;
}
