package com.eshoppingzone.paymentservice.controllers;


import com.eshoppingzone.paymentservice.models.Wallet;
import com.eshoppingzone.paymentservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    WalletService walletService;

    @Autowired
    public PaymentController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/pay")
    public ResponseEntity<?> pay(@RequestParam String userId, @RequestParam double amount){
        try{
            Wallet wallet =  this.walletService.getByUserId(userId);
            if(wallet == null){
                wallet = new Wallet();
                wallet.setUserId(userId);
                wallet.setTotalAmount(0);
                this.walletService.saveWallet(wallet);
            }

            if(wallet.getTotalAmount()>=amount){
                wallet.setTotalAmount(wallet.getTotalAmount()-amount);
                return new ResponseEntity<>("Success",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Transaction failed due to insufficient wallet credits",HttpStatus.OK);
            }


        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getWalletDetails(@RequestParam String userId){
        try{

            Wallet wallet =  this.walletService.getByUserId(userId);
            if(wallet == null){
                wallet = new Wallet();
                wallet.setUserId(userId);
                wallet.setTotalAmount(0);
                this.walletService.saveWallet(wallet);
            }

            return new ResponseEntity<>(wallet,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-wallet")
    public ResponseEntity<?> updateWallet(@RequestParam String userId, @RequestParam double amount){
        try{
            Wallet wallet =  this.walletService.getByUserId(userId);
            wallet.setTotalAmount(wallet.getTotalAmount()+amount);
            walletService.saveWallet(wallet);
            return new ResponseEntity<>(wallet,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
