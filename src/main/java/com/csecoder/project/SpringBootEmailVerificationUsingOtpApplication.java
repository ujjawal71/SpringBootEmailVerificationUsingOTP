package com.csecoder.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories
public class SpringBootEmailVerificationUsingOtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmailVerificationUsingOtpApplication.class, args);

		System.out.println("SpringBootEmailVerificationUsingOtp");
	}

}
