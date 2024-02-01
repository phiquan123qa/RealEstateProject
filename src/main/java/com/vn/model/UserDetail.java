package com.vn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {
    private Long id;
    private LocalDate dob;
    private Long phoneNumber;
    private String email;
    private String city;
    private String district;
    private String ward;
    private Integer point;
    private Boolean status;

}
