package com.arcs.cibus.server;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.enums.Profile;
import com.arcs.cibus.server.repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {		
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		User userAdmin = User
				.builder()
				.name("Admin")
				.email("alyssonr.1993@gmail.com")
				.isFirstLogin(Boolean.FALSE)
				.image("https://www.emaisgoias.com.br/wp-content/uploads/2020/01/Velozes-e-Furiosos-9-Filme-ganha-novo-teaser-Vin-Diesel-620x328.jpg")
				.pass(passwordEncoder.encode("admin"))
				.build();
		
		userAdmin.addProfile(Profile.ADMIN);
		
		User userSalesman = User
				.builder()
				.name("Salesman")
				.email("alysson_salgado@hotmail.com")
				.isFirstLogin(Boolean.TRUE)
				.image("https://abrilveja.files.wordpress.com/2016/06/shia-2014-01-11-original1.jpeg")
				.pass(passwordEncoder.encode("admin"))
				.build();
		
		userSalesman.addProfile(Profile.ADMIN);
	
		userRepository.saveAll(Arrays.asList(userAdmin, userSalesman));
	}
}
