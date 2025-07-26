package com.ecommerce.service;

public interface UserService {
    boolean createUser(String userName, String userEmail, String userPassword, String userPasswordVerify);
}
