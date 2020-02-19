package com.arcs.cibus.server;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arcs.cibus.server.domain.View;
import com.arcs.cibus.server.repository.ViewRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private ViewRepository viewRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		View home = View
				.builder()
				.name("Home")
				.path("/")
				.build();
		
		View user = View
				.builder()
				.name("User")
				.path("/user")
				.build();
		
		View product = View
				.builder()
				.name("Product")
				.path("/product")
				.build();
		
		View category = View
				.builder()
				.name("Category")
				.path("/category")
				.build();
		
		View profile = View
				.builder()
				.name("Profile")
				.path("/profile")
				.build();
		

		viewRepository.saveAll(Arrays.asList(home, user, category, product, profile));
	}
}
