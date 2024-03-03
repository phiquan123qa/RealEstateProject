package com.vn.service;

import com.vn.entity.User;
import com.vn.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public User createUser(User user) {
//        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
//            throw new DuplicateKeyException("User with the same email already exists");
//        }
//        User userEntity = new User();
//        BeanUtils.copyProperties(user, userEntity);
//        userRepository.save(userEntity);
//        return user;
//    }

    public List<User> getAllUsers() {
        try {
            List<User> userEntities = userRepository.findAll();
            return userEntities;
//                    .stream()
//                    .map(user -> new User(
//                            user.getId(),
//                            user.getName(),
//                            user.getEmail(),
//                            user.getAvatar(),
//                            user.getPassword(),
//                            user.getDob(),
//                            user.getPhoneNumber(),
//                            user.getCity(),
//                            user.getDistrict(),
//                            user.getWard(),
//                            user.getPoint(),
//                            user.getStatus(),
//                            user.getRole())
//                    .collect(Collectors.toList()));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving users", e);
        }
    }

//    @Override
//    public User getUserById(Long id) {
//        Optional<User> userEntityOptional = userRepository.findById(id);
//        if (userEntityOptional.isPresent()) {
//            User userEntity = userEntityOptional.get();
//            User user = new User();
//            BeanUtils.copyProperties(userEntity, user);
//            return user;
//        } else {
//            throw new EntityNotFoundException("User not found");
//        }
//    }

//    @Override
//    public User updateUser(Long id, User user) {
//        try {
//            User userEntity = userRepository.findById(id)
//                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
//            BeanUtils.copyProperties(user, userEntity, "id", "userDetail", "reList");
//            userRepository.save(userEntity);
//            return user;
//        } catch (EntityNotFoundException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new RuntimeException("Error updating user with id: " + id, e);
//        }
//    }
//
//    @Override
//    public Optional<User> findByEmail(String searchTerm) {
//        return userRepository.findByEmail(searchTerm)
//                .map(userEntity -> {
//                    User user = new User();
//                    BeanUtils.copyProperties(userEntity, user);
//                    return Optional.of(user);
//                })
//                .orElse(Optional.empty());
//    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow();
    }


}
