package com.arcs.cibus.server.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.security.JWTUtil;
import com.arcs.cibus.server.service.UserService;

@RestController
@RequestMapping(value = "/login")
public class LoginResource {
	
	@Autowired
	private JWTUtil jwtUtil;	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Boolean> isAtivo(HttpServletRequest request) throws Exception {

		String token = request.getHeader("Authorization");
		
		if(token != null && token.startsWith("Bearer ")){
			Boolean isTokenValid = jwtUtil.isTokenValid(token);
			return ResponseEntity.ok(isTokenValid);
		}
		
		return ResponseEntity.ok(false);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<User> newPassword(@RequestBody User user) throws Exception {
		User newUser = userService.getById(user.getId());
		newUser.setPass(user.getPass());
		newUser.setFirstLogin(Boolean.TRUE);
		newUser = userService.save(newUser);
		return ResponseEntity.ok(newUser);			
	}

	@RequestMapping(value="/account/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<User> confirmAccount(@PathVariable Long userId) throws Exception {		
		User user = userService.getById(userId);
		user.setEmailConfirmed(Boolean.TRUE);
		user = userService.save(user);
		return ResponseEntity.ok(user);			
	}
}
