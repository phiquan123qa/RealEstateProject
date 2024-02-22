package com.vn.model;

import com.vn.entity.RealEstateDetailEntity;
import com.vn.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealEstate {
    private Long id;
    private String title;
    private Double price;
    private Integer landArea;
    private byte[] mainImage;
    private RealEstateDetailEntity realEstateDetail;
    private UserEntity owner;
}
