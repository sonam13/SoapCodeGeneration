package com.tcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.tcs")
public class SoapCodeGenerationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapCodeGenerationApplication.class, args);
	}
}
