package com.vn.service;

import com.vn.entity.UserDetailEntity;
import com.vn.entity.UserEntity;
import com.vn.model.User;
import com.vn.model.UserDetail;
import com.vn.repository.UserDetailRepository;
import com.vn.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
        UserEntity userEntity = new UserEntity();
        UserDetailEntity userDetailEntity = new UserDetailEntity();
        BeanUtils.copyProperties(user.getUserDetail(), userDetailEntity);
        BeanUtils.copyProperties(user, userEntity);
        userDetailEntity.setUser(userEntity);
        userEntity.setUserDetail(userDetailEntity);
        userRepository.save(userEntity);
        userDetailRepository.save(userDetailEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities
                .stream()
                .map(userEntity -> new User(userEntity.getId(),
                        userEntity.getFirstName(),
                        userEntity.getLastName(),
                        userEntity.getAvatar(),
                        userEntity.getRole(),
                        userEntity.getUserDetail(),
                        userEntity.getReList()))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public User updateUser(Long id, User user) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setAvatar(user.getAvatar());
        userEntity.setRole(user.getRole());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setUserDetail(user.getUserDetail());
        userEntity.setReList(user.getReList());
        userRepository.save(userEntity);
        return user;
    }


}
