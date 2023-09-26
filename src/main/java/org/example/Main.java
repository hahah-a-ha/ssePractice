package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class,args);
        System.out.println("Hello World!");
        System.out.println("it's just test");
        System.out.println("it's just test2");
        System.out.println("it's just test3");
    }
}