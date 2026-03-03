package com.example.restapi.service;


import com.example.restapi.entity.Employee;
import com.example.restapi.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;


    public Employee saveOrUpdateEmployee(Employee emp){
        return employeeRepo.save(emp);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepo.findById(id).orElse(null);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteById(id);
    }


}
