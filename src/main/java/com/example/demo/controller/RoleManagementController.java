package com.example.demo.controller;

import com.example.demo.model.RoleManagement;
import com.example.demo.service.HelperService;
import com.example.demo.service.RoleManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role-management")
public class RoleManagementController {

    @Autowired
    private RoleManagementService roleManagementService;

    @Autowired
    private HelperService helperService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RoleManagement save(@RequestBody RoleManagement roleManagement) throws ParseException {
        roleManagement.setCreatedDate(helperService.getDateNow());
        return roleManagementService.save(roleManagement);
    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public List<RoleManagement> findAll() {
        return roleManagementService.findAll();
    }

    @RequestMapping(value = "/find-by-id", method = RequestMethod.GET)
    public Optional<RoleManagement> findById(@RequestParam Integer roleId) {
        return roleManagementService.findById(roleId);
    }
}
