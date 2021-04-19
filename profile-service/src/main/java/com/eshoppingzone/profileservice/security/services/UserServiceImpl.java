package com.eshoppingzone.profileservice.security.services;

import com.eshoppingzone.profileservice.models.User;
import com.eshoppingzone.profileservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return this.userRepository.findById(id).get();
    }
}


