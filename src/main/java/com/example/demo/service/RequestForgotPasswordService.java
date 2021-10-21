package com.example.demo.service;

import com.example.demo.dao.RequestForgotPasswordDao;
import com.example.demo.model.RequestForgotPassword;
import com.example.demo.model.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
public class RequestForgotPasswordService {

    @Autowired
    RequestForgotPasswordDao requestForgotPasswordDao;

    @Autowired
    HelperService helperService;

    public RequestForgotPassword save(RequestForgotPassword requestForgotPassword){
        return requestForgotPasswordDao.save(requestForgotPassword);
    }

    public RequestForgotPassword getOne(String reqId){
        return requestForgotPasswordDao.getOne(reqId);
    }

    public RequestForgotPassword update(String reqId, int status) throws ParseException {
        RequestForgotPassword requestForgotPasswordToUpdate = requestForgotPasswordDao.getOne(reqId);
        requestForgotPasswordToUpdate.setStatus(status);
        requestForgotPasswordToUpdate.setUpdatedDate(helperService.getDateNow());
        return requestForgotPasswordDao.save(requestForgotPasswordToUpdate);
    }

    public RequestForgotPassword reqIdIsWaitingApproval(String reqId){
        return requestForgotPasswordDao.reqIdIsWaitingApproval(reqId);
    }
}
