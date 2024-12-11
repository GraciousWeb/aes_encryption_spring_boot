package com.grace.aes_encryption_spring_boot.controller;

import com.grace.aes_encryption_spring_boot.config.EncryptionConfig;
import com.grace.aes_encryption_spring_boot.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/api/encryption")
public class EncryptionController {

    private final EncryptionService encryptionService;
    private final SecretKey secretKey;

    @Autowired
    public EncryptionController(EncryptionService encryptionService) throws Exception {
        this.encryptionService = encryptionService;
        this.secretKey = EncryptionConfig.generateKey();
    }

    @PostMapping("/encrypt")
    public String encrypt(@RequestBody String plainText) throws Exception {
        return encryptionService.encrypt(plainText, secretKey);
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String encryptedText) throws Exception {
        return encryptionService.decrypt(encryptedText, secretKey);
    }
}

