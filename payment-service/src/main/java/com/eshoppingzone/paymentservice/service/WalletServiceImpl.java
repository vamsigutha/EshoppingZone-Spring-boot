package com.eshoppingzone.paymentservice.service;

import com.eshoppingzone.paymentservice.models.Wallet;
import com.eshoppingzone.paymentservice.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService{

    WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet getByUserId(String userId) {
        return this.walletRepository.findByUserId(userId);
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        this.walletRepository.save(wallet);
        return null;
    }
}
