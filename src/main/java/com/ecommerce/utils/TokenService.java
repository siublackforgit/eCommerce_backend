package com.ecommerce.utils;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TokenService {
    public String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }
}
