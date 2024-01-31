package com.vn.service;

import com.vn.entity.UserEntity;
import com.vn.model.User;
import com.vn.model.UserDetail;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    User findByEmail(String searchTerm);
    User findByPhoneNumber(String searchTerm);
}
