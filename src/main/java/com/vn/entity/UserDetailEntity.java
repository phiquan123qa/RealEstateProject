package com.vn.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity(name = "userDetail")
@Data
public class UserDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ud_id")
    private Long id;
    private LocalDate dob;
    @Size(min = 10, max = 13)
    private Long phoneNumber;
    @Email
    @Size(max = 255)
    @Column(unique = true)
    private String email;
    private String city;
    private String district;
    private String ward;
    private Integer point;
    private Boolean status;
}
