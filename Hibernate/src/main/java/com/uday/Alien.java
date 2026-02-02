package com.uday;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity


public class Alien {

    @Id
    private int aid;
    private String aname;
    private String acolor;


    public int getAid() {
        return aid;
    }

    public String getAcolor() {
        return acolor;
    }

    public String getAname() {
        return aname;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public void setAcolor(String acolor) {
        this.acolor = acolor;
    }

    @Override
    public String toString() {
        return "Alien [id=" + aid + ", name=" + aname + ", color=" + acolor + "]";
    }
}
