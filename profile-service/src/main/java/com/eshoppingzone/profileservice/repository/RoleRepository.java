package com.eshoppingzone.profileservice.repository;

import com.eshoppingzone.profileservice.models.ERole;
import com.eshoppingzone.profileservice.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role,String> {
    Optional<Role> findByName(ERole name);
}
