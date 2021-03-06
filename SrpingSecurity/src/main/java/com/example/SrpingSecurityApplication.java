package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.spring.security")
public class SrpingSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrpingSecurityApplication.class, args);
	}

}
