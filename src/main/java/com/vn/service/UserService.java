package com.vn.service;

import com.vn.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    Optional<User> findByEmail(String searchTerm);
}