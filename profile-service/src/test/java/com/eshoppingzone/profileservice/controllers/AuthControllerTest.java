package com.eshoppingzone.profileservice.controllers;

import com.eshoppingzone.profileservice.models.User;
import com.eshoppingzone.profileservice.repository.RoleRepository;
import com.eshoppingzone.profileservice.repository.UserRepository;
import com.eshoppingzone.profileservice.security.jwt.AuthEntryPointJwt;
import com.eshoppingzone.profileservice.security.jwt.JwtUtils;
import com.eshoppingzone.profileservice.security.services.UserDetailsServiceImpl;
import com.eshoppingzone.profileservice.security.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@EnableWebMvc
class AuthControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private AuthEntryPointJwt authEntryPointJwt;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    AuthController authController;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private WebApplicationContext context;

    private User user;

    @BeforeEach
    void setUp() {

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
    void updateUserDetails() throws Exception {



        user = new User();
        user.setId("sdjfkdjs");
        user.setUsername("vamsi");
        user.setEmail("vamsi@gmail.com");
        user.setPassword("ddslkjfdlskjf");
        user.setImage("dlkfjdksjf");
        user.setMobileNumber(9182120);
        user.setDateOfBirth("18-01-2000");

        when(userService.getUserById(anyString())).thenReturn(user);


        user.setGender("male");

//        ResponseEntity<User> entity = new ResponseEntity<>(HttpStatus.OK);
//
//        when(authController.updateUserDetails(any(User.class))).thenCallRealMethod();

        String url="/update-user";
       MvcResult result =  mockMvc.perform(put(url)
               .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
               .characterEncoding("UTF-8")
                        .content(this.mapper.writeValueAsBytes(user))
        ).andExpect(status().isOk()).andReturn();

       String jsonResult = result.getResponse().getContentAsString();

       System.out.println("result"+jsonResult);

//       assertEquals(asJsonString(this.user),jsonResult);



    }

    @Test
    void getUserDetails() throws Exception {
        when(userService.getUserById(anyString())).thenReturn(this.user);


        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.get("/get-user")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id","kdjfdk")
        ).andExpect(status().isOk()).andReturn();


        String jsonResult = result.getResponse().getContentAsString();

        System.out.println("result"+jsonResult);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}