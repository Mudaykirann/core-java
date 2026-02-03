package com.uday;


import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeName {
    private String fname;
    private String lname;
    private String sname;


    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getSname() {
        return sname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
