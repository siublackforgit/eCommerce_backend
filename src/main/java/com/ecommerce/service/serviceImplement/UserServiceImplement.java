package com.ecommerce.service.serviceImplement;

import com.ecommerce.entity.User;
import com.ecommerce.entity.UserExample;
import org.apache.logging.log4j.LogManager;
import com.ecommerce.constant.ResponseCode;
import com.ecommerce.constant.ResultCode;
import com.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class UserServiceImplement  implements UserService {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserService.class);

    @Override
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
        user.setUserName(userName);
        user.setUserEmail(userEmail);
        user.setUserActive(true);
        user.setUserCreatedAt(new Date());







        return false;
    }
}
