package com.vn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "realEstateDetail")
@Data
public class RealEstateDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String district;
    private String ward;
    private String address;
    private String description;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Integer type;
    private String status;
    private Double latitude;
    private Double longitude;
}
