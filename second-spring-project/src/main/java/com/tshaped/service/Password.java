package com.tshaped.service;

public class Password {

    String algo;
    public  Password(String a){
        this.algo=a;
        System.out.println("Password bean is created");
    }
    public String aboutAlgo(){
        return "Algo used is "+ algo;
    }
}
