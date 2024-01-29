package com.az.gamemarketplaceapi;

import com.az.gamemarketplaceapi.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class GameMarketplaceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameMarketplaceApiApplication.class, args);
	}

}
