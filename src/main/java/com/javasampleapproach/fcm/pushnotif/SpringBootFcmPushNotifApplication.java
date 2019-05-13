package com.javasampleapproach.fcm.pushnotif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootFcmPushNotifApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFcmPushNotifApplication.class, args);
	}
}
