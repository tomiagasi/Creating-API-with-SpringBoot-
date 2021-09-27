package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.security.jwt.JwtTokenUtil;
import com.example.demo.security.jwt.JwtUserDetailsService;
import com.example.demo.service.RoleManagementService;
import com.example.demo.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-management")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private RoleManagementService roleManagementService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    private ErrorCode errorCode = new ErrorCode();

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody UserManagement userManagement) {
        if(userManagementService.findById(userManagement.getUsername()).isPresent()){
            errorCode.setCode(Constants.USERNAME_EXIST[0]);
            errorCode.setMessage(Constants.USERNAME_EXIST[1]);
            return ResponseEntity.badRequest().body(errorCode);
        }

        if(roleManagementService.findById(userManagement.getRoleId()).isPresent() == false){
            errorCode.setCode(Constants.ROLE_NOT_AVAILABLE[0]);
            errorCode.setMessage(Constants.ROLE_NOT_AVAILABLE[1]);
            return ResponseEntity.badRequest().body(errorCode);
        }

        try{
            return ResponseEntity.ok(userManagementService.save(userManagement));
        }catch (Exception e){
            errorCode.setCode(Constants.METHOD_ERROR[0]);
            errorCode.setMessage(Constants.METHOD_ERROR[1]);
            return ResponseEntity.unprocessableEntity().body(errorCode);

        }

    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public List<UserManagement> findAll() {
        return userManagementService.findAll();
    }

    @RequestMapping(value = "/find-by-id", method = RequestMethod.GET)
    public Optional<UserManagement> findById(@RequestParam String username) {
        return userManagementService.findById(username);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest){
        try {
            userManagementService.authentication(signinRequest.getUsername(), signinRequest.getPassword());
            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(signinRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            SignInResponse signInResponse = new SignInResponse();
            signInResponse.setUsername(signinRequest.getUsername());
            signInResponse.setToken(token);
            return ResponseEntity.ok(signInResponse);
        }catch (Exception e){
            errorCode.setCode(Constants.INVALID_USERNAME_OR_PASSWORD[0]);
            errorCode.setMessage(Constants.INVALID_USERNAME_OR_PASSWORD[1]);
            return ResponseEntity.badRequest().body(errorCode);
        }


    }
}
