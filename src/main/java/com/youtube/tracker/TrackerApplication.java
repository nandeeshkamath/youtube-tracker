package com.youtube.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableFeignClients
public class TrackerApplication {
	public static void main(String[] args) {
		System.out.println("timeeee  "+DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
		SpringApplication.run(TrackerApplication.class, args);
	}
}