package com.tshaped.config;
// Defines the package where the configuration class is located

import com.tshaped.service.Password;
// Imports the Password class to be used as a Spring bean

import org.springframework.context.annotation.Bean;
// Imports @Bean annotation to define beans manually

import org.springframework.context.annotation.ComponentScan;
// Imports @ComponentScan to enable component scanning

import org.springframework.context.annotation.Configuration;
// Imports @Configuration to mark this class as a Spring configuration class

@Configuration
// Marks this class as a source of Spring bean definitions

@ComponentScan(basePackages = {"com.tshaped"})
// Tells Spring to scan the specified package for stereotype-annotated components

public class Config {

    public Config(){
        System.out.println("Config Bean is created.");
    }

    @Bean
    // Marks this method as a bean producer method

    public Password createPass(){
        // Defines a method that returns a Password object to be managed by Spring
        return new Password("SHA");
        // Creates and returns a Password bean with the SHA algorithm
    }
}
