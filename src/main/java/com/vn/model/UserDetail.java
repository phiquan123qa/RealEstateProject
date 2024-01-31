package com.vn.model;

import com.vn.entity.UserEntity;
import lombok.Data;
import java.time.LocalDate;

@Data
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
    public UserDetail() {
    }

    public UserDetail(Long id, LocalDate dob, Long phoneNumber,
                      String email, String city, String district, String ward,
                      Integer point, Boolean status) {
        this.id = id;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.point = point;
        this.status = status;
    }
}
