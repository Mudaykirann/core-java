package com.uday;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Laptop {

    @Id
    private  int lid;
    private String lname;

    @ManyToMany
    private List<Student> student = new ArrayList<Student>();

    public String getLname() {
        return lname;
    }

    public int getLid() {
        return lid;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }
}
