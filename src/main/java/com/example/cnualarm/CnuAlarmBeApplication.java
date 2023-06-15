package com.example.cnualarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication

public class CnuAlarmBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CnuAlarmBeApplication.class, args);
	}

}
