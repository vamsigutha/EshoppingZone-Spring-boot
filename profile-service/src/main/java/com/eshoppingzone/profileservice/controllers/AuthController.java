package com.eshoppingzone.profileservice.controllers;

import com.eshoppingzone.profileservice.models.ERole;
import com.eshoppingzone.profileservice.models.Role;
import com.eshoppingzone.profileservice.models.User;
import com.eshoppingzone.profileservice.payload.request.LoginRequest;
import com.eshoppingzone.profileservice.payload.request.SignupRequest;
import com.eshoppingzone.profileservice.payload.response.JwtResponse;
import com.eshoppingzone.profileservice.payload.response.MessageResponse;
import com.eshoppingzone.profileservice.repository.RoleRepository;
import com.eshoppingzone.profileservice.repository.UserRepository;
import com.eshoppingzone.profileservice.security.jwt.JwtUtils;
import com.eshoppingzone.profileservice.security.services.UserDetailsImpl;
import com.eshoppingzone.profileservice.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role CUSTOMER = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(CUSTOMER);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "DELIVERYAGENT":
                        Role DELIVERYAGENT = roleRepository.findByName(ERole.ROLE_DELIVERYAGENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(DELIVERYAGENT);

                        break;
                    case "MERCHANT":
                        Role MERCHANT = roleRepository.findByName(ERole.ROLE_MERCHANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(MERCHANT);

                        break;
                    default:
                        Role CUSTOMER = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(CUSTOMER);

                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUserDetails(@RequestBody User user){
        try{
            this.userService.updateUser(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-user")
    public ResponseEntity<?> getUserDetails(@RequestParam String id){
        try{
            User user = this.userService.getUserById(id);
            User filteredUser = new User();
            filteredUser.setId(user.getId());
            filteredUser.setEmail(user.getEmail());
            filteredUser.setUsername(user.getUsername());
            filteredUser.setRoles(user.getRoles());
            filteredUser.setAddressList(user.getAddressList());
            filteredUser.setDateOfBirth(user.getDateOfBirth());
            filteredUser.setGender(user.getGender());
            filteredUser.setImage(user.getImage());
            filteredUser.setMobileNumber(user.getMobileNumber());


            return new ResponseEntity<>(filteredUser, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
