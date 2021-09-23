package com.example.demo.service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Optional<Employee> findById(Integer nip){
        return employeeDao.findById(nip);
    }

     public void deleteById(Integer nip){
        employeeDao.deleteById(nip);
     }

     @Transactional
     public String updateById(Employee employee){
        String forReturn = null;
        try{
            employeeDao.deleteById(employee.getNip());
            employeeDao.save(employee);
            forReturn = "Data berhasil di perbarui";
        }catch (Exception e){
            forReturn = e.getMessage();
        }
        return forReturn;
     }
}
