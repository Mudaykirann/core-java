package com.tshaped.springbootApp1.service;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class Greetings {

    {
        // Instance initializer block executed before the constructor
        System.out.println("Java Block");
        // Prints message when object creation starts
    }

    public Greetings(){
        System.out.println("Greetings bean is created.");
        // Prints message when Spring creates the bean instance
    }

    @PostConstruct
    // Marks a method to run after dependency injection is complete
    public void init(){
        System.out.println("Bean init method");
        // Executes after constructor and before the bean is ready to use
    }

    public String generateWish(){
        return "Good Night \n";
    }

    @PreDestroy
    // Marks a method to run before the bean is removed from the container
    public void destroy(){
        // Cleanup callback method
        System.out.println("Bean is destroyed");
        // Executes when the application context is shutting down
    }
}