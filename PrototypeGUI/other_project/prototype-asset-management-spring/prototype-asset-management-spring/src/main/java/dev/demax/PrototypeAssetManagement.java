package dev.demax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class PrototypeAssetManagement {
	public static void main(String[] args) {
		SpringApplication.run(PrototypeAssetManagement.class, args);
	}
}
