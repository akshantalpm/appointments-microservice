package com.example.microservicespike.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppointmentsMain {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "appointments-server");
        SpringApplication.run(AppointmentsMain.class, args);
    }
}
