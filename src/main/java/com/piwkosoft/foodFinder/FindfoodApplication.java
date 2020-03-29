package com.piwkosoft.foodFinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FindfoodApplication{

    public static void main(String[] args) {
        SpringApplication.run(FindfoodApplication.class, args);
    }
}
