package com.ecommerce.controller;

import com.ecommerce.constant.ResponseCode;
import com.ecommerce.constant.ResultCode;
import com.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<ResponseCode> createUser (
            HttpSession session,
            @RequestBody Map<String, Object> data
    ){

        String userName = (String) data.get("userName");
        String userEmail = (String) data.get("userEmail");
        String userPassword = (String) data.get("userPassword");
        String userPasswordVerify = (String) data.get("userPasswordVerify");

        return ResponseEntity.ok().cacheControl(CacheControl.noCache())
                .body(new ResponseCode(userService.createUser(userName,userEmail,userPassword,userPasswordVerify) ? ResultCode.SUCCESS : ResultCode.OPERATION_FAIL));
    }
}
