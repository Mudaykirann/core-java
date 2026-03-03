package com.example.restapi.controller;

import com.example.restapi.entity.Employee;
import com.example.restapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marks this class as a REST controller so Spring can handle HTTP requests and return JSON responses
@RestController

// Defines the base URL path for all endpoints in this controller
@RequestMapping("/api/employee")
public class EmployeeController {

    // Injects the EmployeeService so we can use business logic inside this controller
    @Autowired
    private EmployeeService employeeService;

    // Handles HTTP POST requests to create a new employee
    // @RequestBody converts incoming JSON into an Employee object
    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveOrUpdateEmployee(employee);
    }

    // Handles HTTP GET requests to retrieve all employees
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    // Handles HTTP GET request with an ID to retrieve a specific employee
    // @PathVariable extracts the ID value from the URL
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    // Handles HTTP DELETE request to remove an employee by ID
    // @PathVariable binds the ID from the URL to the method parameter
    @DeleteMapping("/{id}")   // <-- Fixed: added {id} in mapping
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }

    // Handles HTTP PUT request to update an existing employee
    // {id} comes from URL and @RequestBody contains updated employee data
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        // Ensures the employee ID from URL is set in the object before saving
        employee.setId(id);
        return employeeService.saveOrUpdateEmployee(employee);
    }

}