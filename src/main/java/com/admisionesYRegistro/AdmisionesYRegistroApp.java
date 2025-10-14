package com.admisionesYRegistro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AdmisionesYRegistroApp implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(AdmisionesYRegistroApp.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// Permite a todos los endpoints recibir peticiones de tu frontend
				registry.addMapping("/**")
						.allowedOrigins("http://127.0.0.1:5501")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*");
			}
		};
	}
}


