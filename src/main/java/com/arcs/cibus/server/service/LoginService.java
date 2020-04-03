package com.arcs.cibus.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.repository.UserRepository;
import com.arcs.cibus.server.service.exceptions.MeLoginException;

@Service
public class LoginService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	public User login(User user) throws Exception, MeLoginException {			
		User newUser = userRepository.findByEmail(user.getEmail());
		boolean passMatches = passwordEncoder.matches(user.getPass(), newUser.getPass());

		if(!passMatches){
			throw new MeLoginException("Login or Password is wrong");			
		}

		return newUser;
	}

	public User createPass(Long userId, User user) throws Exception {			
		User newUser = userService.getById(userId);
		
		if(!newUser.getEmail().equals(user.getEmail()))
			throw new MeLoginException("Login is wrong");
		
		newUser.setPass(passwordEncoder.encode(user.getPass()));
		
		return userService.save(newUser);
	}
}
