package com.login.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.login.entity")
@ComponentScan({ "com.login.controller,com.login.aspect,com.login.servicelmp,com.login.util" })
@EnableJpaRepositories("com.*.repository")
public class LoginExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginExampleApplication.class, args);
	}

}
