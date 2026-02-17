package com.tshaped.springbootApp1;

import com.tshaped.springbootApp1.service.Greetings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication //Equivalent to ComponentScan + @EnableAutoConfiguration
public class SpringbootApp1Application {

	public static void main(String[] args) {

        ConfigurableApplicationContext container =  SpringApplication.run(SpringbootApp1Application.class, args);
        Greetings greet = container.getBean(Greetings.class);
        System.out.print(greet.generateWish());
    }
}
