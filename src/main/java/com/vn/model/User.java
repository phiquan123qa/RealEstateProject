package com.vn.model;

import com.vn.entity.UserDetailEntity;
import com.vn.entity.UserLikeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String avatar;
    private String role;
    private UserDetailEntity userDetail;
    private List<UserLikeEntity> reList;
}
