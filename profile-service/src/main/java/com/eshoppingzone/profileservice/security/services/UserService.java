package com.eshoppingzone.profileservice.security.services;

import com.eshoppingzone.profileservice.models.User;

public interface UserService {
    void updateUser(User user);
    User getUserById(String id);
}
