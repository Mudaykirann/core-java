package com.tshaped.repo;


import org.springframework.stereotype.Repository;

@Repository
public class Repo {
    public Repo(){
        System.out.println("Repo bean is created.");
    }
}
