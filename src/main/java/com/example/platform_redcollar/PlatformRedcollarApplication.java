package com.example.platform_redcollar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example", "common.lib"})
public class PlatformRedcollarApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformRedcollarApplication.class, args);
	}

}
