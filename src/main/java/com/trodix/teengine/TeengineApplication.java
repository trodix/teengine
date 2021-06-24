package com.trodix.teengine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeengineApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TeengineApplication.class, args);
	}

	@Override
    public void run(String... args) {
        // Do Something
    }

}
