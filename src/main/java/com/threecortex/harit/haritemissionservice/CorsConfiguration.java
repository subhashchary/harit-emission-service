/**
 * Copyright 3Cortex Technologies Pvt Ltd
 */
package com.threecortex.harit.haritemissionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class is used to enable CORS configuration for rest APIs globally. CORs
 * signify Cross Origin Requests
 * 
 * @author Rajendra
 *
 */
@Configuration
public class CorsConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(CorsConfiguration.class);
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		logger.debug("Intercepted *****CorsConfiguration");
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT").allowedHeaders("*").allowedOrigins("*");
			}

		};
	}	
}
