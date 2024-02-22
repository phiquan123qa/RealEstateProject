package com.vn.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealEstateDetail {
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
