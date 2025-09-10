package com.dajava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAsync
@SpringBootApplication(scanBasePackages = "com.dajava")
@EntityScan(basePackages = "com.dajava")            // @Entity 분산 시 안전
@EnableJpaRepositories(basePackages = "com.dajava") // JpaRepository 스캔
public class BootApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
}
