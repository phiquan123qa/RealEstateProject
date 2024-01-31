package com.vn.model;

import com.vn.entity.UserDetailEntity;
import com.vn.entity.UserLikeEntity;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String avatar;
    private String role;
    private UserDetailEntity userDetail;
    private List<UserLikeEntity> reList;

    public User(Long id, String firstName, String lastName, String avatar, String role, UserDetailEntity userDetail, List<UserLikeEntity> reList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.role = role;
        this.userDetail = userDetail;
        this.reList = reList;
    }

    public User() {
    }
}
