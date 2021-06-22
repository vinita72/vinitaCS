package com.FlightBookingSystem.SecurePassenger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class, 
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class
})
@CrossOrigin(origins ="http://localhost:4200/")
@EnableEurekaClient
@EnableSwagger2
public class SecurePassengerApplication{
	
	private static final Logger Log =LoggerFactory.getLogger(SecurePassengerApplication.class);
	
	
	public static void main(String[] args) {
		
		Log.info("Application Started");
		SpringApplication.run(SecurePassengerApplication.class, args);
		
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/v1/users").allowedOrigins("http://localhost:4200");
			}
		};
	}
	
	
	
}
	
	/*
	 * public void run(String... args) throws Exception { // TODO Auto-generated
	 * method stub
	 * 
	 * 
	 * User s1 = new User("7",
	 * "ravi","$2a$04$qfQfLnh79PUog6nwmO7noOY1azw4tPYGKvKCR8NXYyHvJD2fKhPt6",
	 * "ravi@gmail.com"); User s2 = new User("9", "Vachi",
	 * "$2a$04$3jankoGXp6uTBk5ZGrHOYO6AGo.WX31.s6kXeUWmPwN7vqB7AlpLy",
	 * "vachi@gmail.com");
	 * 
	 * 
	 * repository.save(s1); repository.save(s2);
	 * 
	 * 
	 * System.out.println("***");
	 * 
	 * 
	 * List<User> users = repository.findAll(); for (User s : users) {
	 * System.out.println(s.toString()); }
	 * 
	 * }
	 */

	
	




	
	