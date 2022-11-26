package com.animal.logic.markovChain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MarkovChainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkovChainApplication.class, args);
	}
}
