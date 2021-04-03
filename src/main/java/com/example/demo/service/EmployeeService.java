package com.example.demo.service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    public Employee save(Employee employee){
        return employeeDao.save(employee);
    }

    public List<Employee> findAll(){
        return employeeDao.findAll();
    }

    public Optional<Employee> findById(Integer id){
        return employeeDao.findById(id);
    }
}
