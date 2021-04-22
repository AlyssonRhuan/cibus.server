package com.arcs.cibus.server.service;

import java.util.Collection;
import java.util.stream.Collectors;

import com.arcs.cibus.server.domain.enums.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.repository.UserRepository;
import com.arcs.cibus.server.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		
		if(user == null){
			throw new UsernameNotFoundException(email);
		}
		
		Long userId = user.getId();
		String userEmail = user.getEmail();
		String userPasspass = user.getPass();
		boolean isEmailConfirmed = user.isEmailConfirmed();
		Profile userProfile = user.getProfile();
		
		return new UserSS(userId, userEmail, userPasspass, isEmailConfirmed, userProfile);
	}	
}
