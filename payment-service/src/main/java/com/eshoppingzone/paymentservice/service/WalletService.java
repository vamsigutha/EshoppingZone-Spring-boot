package com.eshoppingzone.paymentservice.service;

import com.eshoppingzone.paymentservice.models.Wallet;

import java.util.Optional;

public interface WalletService {
    Wallet getByUserId(String userId);
    Wallet saveWallet(Wallet wallet);
}
