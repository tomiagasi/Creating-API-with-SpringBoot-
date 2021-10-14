package com.example.demo.controller;

import com.example.demo.model.Constants;
import com.example.demo.model.RequestForgotPassword;
import com.example.demo.model.UserManagement;
import com.example.demo.model.requestbody.ForgotPassword;
import com.example.demo.model.requestbody.Mail;
import com.example.demo.model.responsebody.ErrorCode;
import com.example.demo.model.requestbody.ChangePassword;
import com.example.demo.service.HelperService;
import com.example.demo.service.MailService;
import com.example.demo.service.RequestForgotPasswordService;
import com.example.demo.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/setting/account")
public class SettingAccountController {

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private RequestForgotPasswordService requestForgotPasswordService;

    @Autowired
    private HelperService helperService;

    @Autowired
    MailService mailService;

    private ErrorCode errorCode = new ErrorCode();

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Boolean checkUser = userManagementService.authentication(username, changePassword.getOldPassword());

        if(!changePassword.getNewPassword().equals(changePassword.getRepeatNewPassword())){
            errorCode.setCode(Constants.PASSWORD_NOT_MATCH[0]);
            errorCode.setMessage(Constants.PASSWORD_NOT_MATCH[1]);
            return ResponseEntity.badRequest().body(errorCode);
        }

        if(checkUser.equals(true)){
            try{
                UserManagement userManagement;
                userManagement = userManagementService.getOne(username);
                userManagement.setPassword(helperService.encryptPassword(changePassword.getNewPassword()));
                userManagementService.update(userManagement);
                errorCode.setCode(Constants.SUCCESS[0]);
                errorCode.setMessage(Constants.SUCCESS[1]);
                return ResponseEntity.ok(errorCode);
            }catch (Exception e){
                errorCode.setCode(Constants.METHOD_ERROR[0]);
                errorCode.setMessage(Constants.METHOD_ERROR[1]);
                return ResponseEntity.unprocessableEntity().body(errorCode);
            }
        }else{
            errorCode.setCode(Constants.INVALID_PASSWORD[0]);
            errorCode.setMessage(Constants.INVALID_PASSWORD[1]);
            return ResponseEntity.badRequest().body(errorCode);
        }
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassword forgotPassword){
        int status = Constants.WAITING_APPROVAL;
        RequestForgotPassword requestForgotPassword = new RequestForgotPassword();

        if(!forgotPassword.getNewPassword().equals(forgotPassword.getRepeatNewPassword())){
            errorCode.setCode(Constants.PASSWORD_NOT_MATCH[0]);
            errorCode.setMessage(Constants.PASSWORD_NOT_MATCH[1]);
            return ResponseEntity.badRequest().body(errorCode);
        }

        if(!userManagementService.findByEmail(forgotPassword.getEmail()).equals(null)){
            try{
                UserManagement userManagement = userManagementService.findByEmail(forgotPassword.getEmail());
                requestForgotPassword.setUsername(userManagement.getUsername());
                requestForgotPassword.setStatus(Constants.WAITING_APPROVAL);
                requestForgotPassword.setPassword(helperService.encryptPassword(forgotPassword.getNewPassword()));
                requestForgotPassword.setStatus(status);
                RequestForgotPassword returnSave = requestForgotPasswordService.save(requestForgotPassword);

                /*
                SEND EMAIL for Verification
                 */
                Mail mail = new Mail();
                mail.setMailFrom("no-reply@tomiagasi.com");
                mail.setMailTo("tomiagasi@gmail.com");
                mail.setMailSubject("Security Alert for "+forgotPassword.getEmail() );


                mail.setMailContent(Constants.PROJECT_URI+"setting/account/update-forgot-password/"+returnSave.getReqId());
                mailService.sendEmail(mail);

                errorCode.setCode(Constants.SUCCESS[0]);
                errorCode.setMessage(Constants.SUCCESS[1]);
                return ResponseEntity.ok(errorCode);
            }catch (Exception e){
                errorCode.setCode(Constants.METHOD_ERROR[0]);
                errorCode.setMessage(Constants.METHOD_ERROR[1]);
                return ResponseEntity.unprocessableEntity().body(errorCode);
            }
        }else{
            errorCode.setCode(Constants.INVALID_EMAIL[0]);
            errorCode.setMessage(Constants.INVALID_EMAIL[1]);
            return ResponseEntity.badRequest().body(errorCode);
        }
    }

    @RequestMapping(value = "/update-forgot-password/{requestId}", method = RequestMethod.GET)
    public ResponseEntity<?> forgotPassword(@PathVariable String requestId){
        try{
            RequestForgotPassword getOne = requestForgotPasswordService.reqIdIsWaitingApproval(requestId);
            UserManagement userManagement = userManagementService.getOne(getOne.getUsername());
            requestForgotPasswordService.update(getOne.getReqId(), Constants.APPROVE);
            userManagement.setPassword(getOne.getPassword());
            userManagementService.update(userManagement);
            errorCode.setCode(Constants.SUCCESS[0]);
            errorCode.setMessage(Constants.SUCCESS[1]);
            return ResponseEntity.ok(errorCode);
        }catch (Exception e){
            errorCode.setCode(Constants.REQID_NOT_AVAILABLE[0]);
            errorCode.setMessage(Constants.REQID_NOT_AVAILABLE[1]);
            return ResponseEntity.badRequest().body(errorCode);
        }
    }
}
