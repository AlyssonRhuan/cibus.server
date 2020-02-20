package com.arcs.cibus.server.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(login);
		
		if(user == null){
			throw new UsernameNotFoundException(login);
		}
		
		Long userId = user.getId();
		String userLogin = user.getLogin();
		String userPasspass = user.getPass();
		Collection<? extends GrantedAuthority> userAuthorities = null;		
		
		return new UserSS(userId, userLogin, userPasspass, userAuthorities);
	}	
}
