package com.arcs.cibus.server;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.View;
import com.arcs.cibus.server.domain.enums.Profile;
import com.arcs.cibus.server.repository.UserRepository;
import com.arcs.cibus.server.repository.ViewRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private ViewRepository viewRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		View home = View
				.builder()
				.name("Home")
				.path("/")
				.level(2)
				.build();
		
		View user = View
				.builder()
				.name("User")
				.path("/user")
				.level(1)
				.build();
		
		View product = View
				.builder()
				.name("Product")
				.path("/product")
				.level(2)
				.build();
		
		View category = View
				.builder()
				.name("Category")
				.path("/category")
				.level(2)
				.build();
		
		User userAdmin = User
				.builder()
				.name("Admin")
				.email("alyssonr.1993@gmail.com")
				.login("admin")
				.pass(passwordEncoder.encode("admin"))
				.build();
		
		userAdmin.addProfile(Profile.ADMIN);
		
		User userSalesman = User
				.builder()
				.name("Salesman")
				.email("alysson_salgado@hotmail.com")
				.login("vendedor")
				.pass(passwordEncoder.encode("vendedor"))
				.build();
		
		userSalesman.addProfile(Profile.SALESMAN);

		viewRepository.saveAll(Arrays.asList(home, user, category, product));		
		userRepository.saveAll(Arrays.asList(userAdmin, userSalesman));
	}
}
