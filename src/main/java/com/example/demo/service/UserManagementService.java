package com.example.demo.service;

import com.example.demo.dao.UserManagementDao;
import com.example.demo.model.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {

    @Autowired
    private UserManagementDao userManagementDao;

    @Autowired
    private HelperService helperService;

    public List<UserManagement> findAll(){
        return userManagementDao.findAll();
    }

    public UserManagement save(UserManagement userManagement){
        userManagement.setPassword(helperService.encryptPassword(userManagement.getPassword()));
        return userManagementDao.save(userManagement);
    }

    public UserManagement update(UserManagement userManagement){
        UserManagement userManagementToUpdate = userManagementDao.getOne(userManagement.getUsername());
        userManagementToUpdate.setUsername(userManagement.getUsername());
        userManagementToUpdate.setPassword(userManagement.getPassword());
        userManagementToUpdate.setFirstName(userManagement.getFirstName());
        userManagementToUpdate.setLastName(userManagementToUpdate.getLastName());
        userManagementToUpdate.setAddress(userManagement.getAddress());
        userManagementToUpdate.setRoleId(userManagement.getRoleId());
        return userManagementDao.save(userManagementToUpdate);
    }

    public Optional<UserManagement> findById(String username){
        return userManagementDao.findById(username);
    }

    public Boolean authentication(String username, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        try{
            String passwordFromTable = userManagementDao.findById(username).get().getPassword();
            return bCryptPasswordEncoder.matches(password, passwordFromTable);
        }catch (Exception e){
            return false;
        }

    }

    public UserManagement getOne(String username){
        return userManagementDao.getOne(username);
    }

    public <Optional>UserManagement findByEmail(String email){
        return userManagementDao.findByEmail(email);
    }
}
