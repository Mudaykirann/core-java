package com.tshaped.main;
// Defines the package name where the LaunchAPP class belongs

import com.tshaped.config.Config;
// Imports the configuration class that contains Spring bean definitions

import com.tshaped.service.Password;
// Imports the Password service class managed by Spring

import org.springframework.context.ApplicationContext;
// Imports Spring's central interface for accessing the IoC container

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// Imports the ApplicationContext implementation for annotation-based configuration


public class LaunchAPP {

    public static void main(String[] args) {

        // ApplicationContext
        // Bean Factory
        // Comments indicating Spring container types (ApplicationContext is preferred)


        // following Annotation approach
        // Indicates that annotation-based configuration is used

        ApplicationContext container = new AnnotationConfigApplicationContext(Config.class);
        // Creates the Spring IoC container using the Config class for bean definitions

        Password pass = container.getBean(Password.class);
        // Retrieves the Password bean from the Spring container by type

        pass.aboutAlgo();
    }
}
