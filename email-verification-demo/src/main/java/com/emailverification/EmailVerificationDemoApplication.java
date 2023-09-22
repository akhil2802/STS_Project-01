package com.emailverification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Blogging Application : Backend API", version = "1.0", description = "This project is developed by Akhilesh", termsOfService = "Terms of Service", contact = @Contact(name = "Akhilesh", email = "akhileshkumar2802@gmail.com", url = "https://techdbugger.com"), license = @License(name = "License of APIS", url = "API License url")))
public class EmailVerificationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailVerificationDemoApplication.class, args);
	}

}
