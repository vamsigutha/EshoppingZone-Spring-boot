package com.eshoppingzone.profileservice.security.services;

import com.eshoppingzone.profileservice.models.Role;
import com.eshoppingzone.profileservice.models.User;
import com.eshoppingzone.profileservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserDetailsServiceImplTest {

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername() {


        User user = new User();
        user.setUsername("vamsi");
        user.setEmail("vamsi@gmail.com");
        user.setPassword("ddslkjfdlskjf");
        user.setImage("dlkfjdksjf");
        user.setMobileNumber(9182120);
        user.setDateOfBirth("18-01-2000");


        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername("vamsi");

        assertNotNull(userDetails);
        assertEquals("ddslkjfdlskjf",userDetails.getPassword());
    }


    @Test
    void loadByUserName_UserNameNotFoundException(){
        when(userRepository.findByUsername(anyString())).thenThrow(new UsernameNotFoundException("username not found"));



            assertThrows(UsernameNotFoundException.class,
                    ()->{
                        userDetailsService.loadUserByUsername("vamsi");
                    }
                );
    }


}