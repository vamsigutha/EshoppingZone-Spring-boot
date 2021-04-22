package com.eshoppingzone.profileservice.security.services;

import com.eshoppingzone.profileservice.models.User;
import com.eshoppingzone.profileservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId("sdjfkdjs");
        user.setUsername("vamsi");
        user.setEmail("vamsi@gmail.com");
        user.setPassword("ddslkjfdlskjf");
        user.setImage("dlkfjdksjf");
        user.setMobileNumber(9182120);
        user.setDateOfBirth("18-01-2000");
    }



    @Test
    void getUserById() {
        when(userRepository.findById(anyString())).thenReturn(java.util.Optional.ofNullable(this.user));

        User testUser = userService.getUserById("sdjfkdjs");

        assertNotNull(testUser);
        assertEquals("vamsi",testUser.getUsername());
    }
}