package com.borangalleri.cardealership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages= {"com.borangalleri"})
@EntityScan(basePackages= {"com.borangalleri"})
@EnableJpaRepositories(basePackages= {"com.borangalleri"})
@SpringBootApplication
public class CardealershipApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardealershipApplication.class, args);
	}

}
