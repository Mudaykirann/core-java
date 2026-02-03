package com.uday;

// 1. Correct the import (adjust the package name if different)
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Embedded;

@Entity
public class Employee {

    @Id
    private int id;

    @Embedded // Explicitly marks this as an embedded object
    private EmployeeName name;

    private String designation;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 2. Return type must be EmployeeName, not String
    public EmployeeName getName() {
        return name;
    }

    public void setName(EmployeeName name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}