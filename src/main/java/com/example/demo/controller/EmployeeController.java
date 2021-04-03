package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @RequestMapping(value = "/find-by-id", method = RequestMethod.GET)
    public Optional<Employee> findById(@RequestParam Integer id) {
        return employeeService.findById(id);
    }
}
