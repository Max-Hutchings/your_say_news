package com.YourSayNews.UserCharacteristicsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserCharacteristicsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCharacteristicsServiceApplication.class, args);
	}

}
