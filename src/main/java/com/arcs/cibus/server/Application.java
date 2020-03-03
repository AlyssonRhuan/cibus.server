package com.arcs.cibus.server;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.View;
import com.arcs.cibus.server.domain.enums.Profile;
import com.arcs.cibus.server.domain.enums.ViewContext;
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
		
		View me = View
				.builder()
				.name("Me")
				.path("/me")
				.level(2)
				.context(ViewContext.PERSONAL)
				.build();
		
		View user = View
				.builder()
				.name("Users")
				.path("/user")
				.level(1)
				.context(ViewContext.ADMINISTRATIVE)
				.build();
		
		View product = View
				.builder()
				.name("Products")
				.path("/product")
				.level(2)
				.context(ViewContext.GENERAL)
				.build();
		
		View category = View
				.builder()
				.name("Categorys")
				.path("/category")
				.level(2)
				.context(ViewContext.GENERAL)
				.build();
		
		User userAdmin = User
				.builder()
				.name("Admin")
				.email("alyssonr.1993@gmail.com")
				.login("admin")
				.image("https://www.emaisgoias.com.br/wp-content/uploads/2020/01/Velozes-e-Furiosos-9-Filme-ganha-novo-teaser-Vin-Diesel-620x328.jpg")
				.pass(passwordEncoder.encode("admin"))
				.build();
		
		userAdmin.addProfile(Profile.ADMIN);
		
		User userSalesman = User
				.builder()
				.name("Salesman")
				.email("alysson_salgado@hotmail.com")
				.login("vendedor")
				.image("https://www.emaisgoias.com.br/wp-content/uploads/2020/01/Velozes-e-Furiosos-9-Filme-ganha-novo-teaser-Vin-Diesel-620x328.jpg")
				.pass(passwordEncoder.encode("vendedor"))
				.build();
		
		userSalesman.addProfile(Profile.SALESMAN);

		viewRepository.saveAll(Arrays.asList(user, category, product, me));		
		userRepository.saveAll(Arrays.asList(userAdmin, userSalesman));
	}
}
