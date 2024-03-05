package com.vn.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vn.entity.RealEstate;
import com.vn.entity.User;
import lombok.Data;
import org.springframework.http.HttpHeaders;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {
    //Request
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private HttpHeaders headers;
    //Create user
    private String name;
    private String email;
    private String avatar;
    private String password;
    private LocalDate dob;
    private Long phoneNumber;
    private String city;
    private String district;
    private String ward;
    private Integer point;
    private Boolean status;
    private String role;
    //Create Real Estate
    private String title;
    private Integer price;
    private Integer landArea;
    private String mainImage;
    private String cityRe;
    private String districtRe;
    private String wardRe;
    private String address;
    private String description;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Integer type;
    private String statusRe;
    private List<RealEstate> realEstates;
    private User user;


}
