package com.example.demo.controller;


import com.example.demo.model.BackendAuditrail;
import com.example.demo.service.BackendAuditrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audittrail")
public class BackendAuditrailController {

    @Autowired
    BackendAuditrailService backendAuditrailService;

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public List<BackendAuditrail> findAll() {
        return backendAuditrailService.findAll();
    }
}
