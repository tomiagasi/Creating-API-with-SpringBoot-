package com.example.demo.service;

import com.example.demo.dao.UserManagementDao;
import com.example.demo.model.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {

    @Autowired
    private UserManagementDao userManagementDao;

    public List<UserManagement> findAll(){
        return userManagementDao.findAll();
    }

    public UserManagement save(UserManagement userManagement){
        return userManagementDao.save(userManagement);
    }

    public Optional<UserManagement> findById(String username){
        return userManagementDao.findById(username);
    }
}
