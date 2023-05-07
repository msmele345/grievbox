package com.mitchmele.grievbox;

import com.mitchmele.grievbox.repository.GrievanceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {GrievanceRepository.class})
public class GrievboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrievboxApplication.class, args);
	}

}
