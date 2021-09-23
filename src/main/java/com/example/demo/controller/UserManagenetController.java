package com.example.demo.controller;

import com.example.demo.model.RoleManagement;
import com.example.demo.model.UserManagement;
import com.example.demo.service.RoleManagementService;
import com.example.demo.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-management")
public class UserManagenetController {

    @Autowired
    private UserManagementService userManagementService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserManagement save(@RequestBody UserManagement userManagement) {
        return userManagementService.save(userManagement);
    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public List<UserManagement> findAll() {
        return userManagementService.findAll();
    }

    @RequestMapping(value = "/find-by-id", method = RequestMethod.GET)
    public Optional<UserManagement> findById(@RequestParam String username) {
        return userManagementService.findById(username);
    }
}
