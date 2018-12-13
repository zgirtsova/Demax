package dev.demax.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfiguration {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	 
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder(5);
	}
}

