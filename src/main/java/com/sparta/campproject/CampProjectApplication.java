package com.sparta.campproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = ContextRegionProviderAutoConfiguration.class)
@EnableJpaAuditing
public class CampProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(CampProjectApplication.class, args);
	}

}
