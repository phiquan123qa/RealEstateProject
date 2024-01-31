package com.vn.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "UserLike")
@Data
public class UserLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
