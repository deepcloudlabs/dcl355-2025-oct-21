package com.example.world;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.world.config.AppConfig;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
@ConfigurationPropertiesScan
public class WorldJdbcSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorldJdbcSpringBootApplication.class, args);
	}

}
