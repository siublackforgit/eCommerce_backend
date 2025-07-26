package com.ecommerce.service.serviceImplement;

import ch.qos.logback.core.util.StringUtil;
import com.ecommerce.eNum.AuthType;
import com.ecommerce.entity.User;
import com.ecommerce.entity.UserAuth;
import com.ecommerce.mapper.UserAuthMapper;


import com.ecommerce.mapper.UserMapper;
import com.ecommerce.utils.TokenService;
import org.apache.logging.log4j.LogManager;
import com.ecommerce.constant.ResponseCode;
import com.ecommerce.constant.ResultCode;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;

public class UserServiceImplement  implements UserService {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserAuthMapper userAuthMapper;

    @Override
    @Transactional
    public boolean createUser(String userName, String userEmail, String userPassword, String userPasswordVerify) {

        //Form Validation
        if (userName == null || userName.isEmpty() ||
                userEmail == null || userEmail.isEmpty() ||
                userPassword == null || userPassword.isEmpty() ||
                userPasswordVerify == null || userPasswordVerify.isEmpty()) {
                    logger.error("Form validation failed: One or more required fields are empty or null. " +
                            "userName={}, userEmail={}, userPassword={}, userPasswordVerify={}",
                    userName, userEmail, "[PROTECTED]", "[PROTECTED]"); // ⚠️ Don't log passwords!

            return false;
        }

        if(userName.length()>20){
            logger.error("userName length is over 20");
            return false;
        }

        if(userPassword.length()>255){
            logger.error("userPassword length is over 20");
            return false;
        }

        if(userEmail.length()>50){
            logger.error("userEmail length is over 50");
            return false;
        }

        if(userEmail.length()>50){
            logger.error("userEmail length is over 50");
            return false;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!userEmail.matches(emailRegex)) {
            logger.error("Invalid userEmail format: {}", userEmail);
            return false;
        }
        //End validation

        User user = new User();
        user.setUserActive(false);
        user.setUserEmail(userEmail);
        user.setUserName(userName);
        user.setUserEmailVerified(false);
        String token = tokenService.generateVerificationToken();
        if (token == null || token.trim().isEmpty()) {
            logger.error("Failed to generate verification token for user: {}", userEmail);
            return false;
        }
        user.setUserEmailVerificationToken(token);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusHours(24);
        Date expirationDate = java.sql.Timestamp.valueOf(expiresAt);
        user.setUserEmailVerificationTokenExpiresAt(expirationDate);


        Long userId = user.getUserId();
        String hashedPassword = passwordEncoder.encode(userPassword);
        UserAuth userAuth = new UserAuth();
        userAuth.setUserauthAuthtype(AuthType.PASSWORD.getCode());
        userAuth.setUserauthPassword(hashedPassword);
        userAuth.setUserauthUserid(userId);
        userAuth.setUserauthIdentifier(userEmail);
        userAuth.setUserauthCreatedAt(new Date());
        userAuthMapper.insert(userAuth);

        return true;
    }
}
