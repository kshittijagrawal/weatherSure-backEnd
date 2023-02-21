package com.example.weatherSure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherSureApplication {

	public static void main(String[] args) {
		System.out.println("Starting..");
		SpringApplication.run(WeatherSureApplication.class, args);
		System.out.println("Finishing..");
	}

}
