package com.company.fs.analytics.restapi.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is a main Spring Boot invocation class. This class is created with the annotation 
 * @SpringBootApplication, the entry point of the spring boot application.
 *
 */
@SpringBootApplication(scanBasePackages = {"com.company.fs.analytics.restapi"})
public class RestEndPoint {

	public static void main(String[] args) {
		SpringApplication.run(RestEndPoint.class, args);
	}
}