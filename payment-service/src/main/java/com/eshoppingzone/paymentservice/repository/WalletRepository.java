package com.eshoppingzone.paymentservice.repository;

import com.eshoppingzone.paymentservice.models.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WalletRepository extends MongoRepository<Wallet,String> {
    Wallet findByUserId(String userId);
}
