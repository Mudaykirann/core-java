package com.uday;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    private int id;
    private String name;
    private int marks;

    @ManyToMany(mappedBy = "student")
    private List<Laptop> l = new ArrayList<Laptop>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public List<Laptop> getL() {
        return l;
    }

    public void setL(List<Laptop> l) {
        this.l = l;
    }

    @Override
    public String toString(){
        return "Student [rollno=" + id + ", name= "+ name + ", marks="+marks + "]";
    }
}
