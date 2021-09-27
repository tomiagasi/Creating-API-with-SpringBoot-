package com.example.demo.service;

import com.example.demo.dao.UserManagementDao;
import com.example.demo.model.ErrorCode;
import com.example.demo.model.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {

    @Autowired
    private UserManagementDao userManagementDao;

    private ErrorCode errorCode = new ErrorCode();

    public List<UserManagement> findAll(){
        return userManagementDao.findAll();
    }

    public UserManagement save(UserManagement userManagement){
        /*
        encryption password
         */
        int strength = 31;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(userManagement.getPassword());
        userManagement.setPassword(encodedPassword);

        return userManagementDao.save(userManagement);
    }

    public Optional<UserManagement> findById(String username){
        return userManagementDao.findById(username);
    }

    public Boolean authentication(String username, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordFromTable = userManagementDao.findById(username).get().getPassword();
        return bCryptPasswordEncoder.matches(password, passwordFromTable);
    }
}
