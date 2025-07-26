package com.ecommerce.service;

import com.ecommerce.constant.ResponseCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface EmailService {
    ResponseEntity<ResponseCode> verifyEmail(@RequestParam String token);
}
