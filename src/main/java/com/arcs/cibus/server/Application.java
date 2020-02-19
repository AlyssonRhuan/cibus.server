package com.arcs.cibus.server;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.arcs.cibus.server.domain.Profile;
import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.View;
import com.arcs.cibus.server.repository.ProfileRepository;
import com.arcs.cibus.server.repository.UserRepository;
import com.arcs.cibus.server.repository.ViewRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private ViewRepository viewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
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
		
		Profile adminProfile = Profile
				.builder()
				.name("Admin")
				.views(Arrays.asList(home, user, category, product, profile))
				.build();
		
		User userAdmin = User
				.builder()
				.name("Admin")
				.email("alyssonr.1993@gmail.com")
				.login("admin")
				.pass(passwordEncoder.encode("admin"))
				.actionAdd(Boolean.TRUE)
				.actionRead(Boolean.TRUE)
				.actionUpdate(Boolean.TRUE)
				.actionRemove(Boolean.TRUE)		
				.profile(adminProfile)
				.build();
		

		viewRepository.saveAll(Arrays.asList(home, user, category, product, profile));
		profileRepository.save(adminProfile);
		userRepository.save(userAdmin);
	}
}
