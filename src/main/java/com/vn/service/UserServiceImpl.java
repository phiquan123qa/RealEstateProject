package com.vn.service;

import com.vn.entity.UserDetailEntity;
import com.vn.entity.UserEntity;
import com.vn.model.User;
import com.vn.repository.UserDetailRepository;
import com.vn.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;

    public UserServiceImpl(UserRepository userRepository, UserDetailRepository userDetailRepository) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByUserDetail_Email(user.getUserDetail().getEmail())) {
            throw new DuplicateKeyException("User with the same email already exists");
        }
        UserEntity userEntity = new UserEntity();
        UserDetailEntity userDetailEntity = new UserDetailEntity();
        BeanUtils.copyProperties(user.getUserDetail(), userDetailEntity);
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setUserDetail(userDetailEntity);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            List<UserEntity> userEntities = userRepository.findAll();
            return userEntities
                    .stream()
                    .map(userEntity -> new User(
                            userEntity.getId(),
                            userEntity.getFirstName(),
                            userEntity.getLastName(),
                            userEntity.getAvatar(),
                            userEntity.getRole(),
                            userEntity.getUserDetail(),
                            userEntity.getReList()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving users", e);
        }
    }

    @Override
    public User getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            return user;
        } else {
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public User updateUser(Long id, User user) {
        try {
            UserEntity userEntity = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

            UserDetailEntity userDetailEntity = userEntity.getUserDetail();
            if (userDetailEntity == null) {
                throw new EntityNotFoundException("User detail not found for user with id: " + id);
            }

            BeanUtils.copyProperties(user, userEntity, "id", "userDetail", "reList");
            BeanUtils.copyProperties(user.getUserDetail(), userDetailEntity, "id");

            userDetailRepository.save(userDetailEntity);
            userRepository.save(userEntity);

            return user;
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating user with id: " + id, e);
        }
    }

    @Override
    public Optional<User> findByEmail(String searchTerm) {
        return userRepository.findByEmail(searchTerm)
                .map(userEntity -> {
                    User user = new User();
                    BeanUtils.copyProperties(userEntity, user);
                    return Optional.of(user);
                })
                .orElse(Optional.empty());
    }


}
