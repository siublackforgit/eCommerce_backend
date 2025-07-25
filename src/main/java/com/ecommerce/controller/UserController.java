package com.ecommerce.controller;

import com.ecommerce.constant.ResponseCode;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

//    @PostMapping("/createUser")
//    public ResponseEntity<ResponseCode> createUser (
//            HttpSession session,
//            @RequestBody Map<String, Object> data
//    ){
//
//    }
}
