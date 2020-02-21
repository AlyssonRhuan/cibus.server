package com.arcs.cibus.server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.service.LoginService;

@RestController
@RequestMapping(value = "/login2")
public class LoginResource {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<User> login(@RequestBody User user) throws Exception {
		user = loginService.login(user);
		return ResponseEntity.ok(user);		
	}
	
	@RequestMapping(value="/{userId}", method = RequestMethod.POST)
	public ResponseEntity<User> createPass(@PathVariable Long userId, @RequestBody User user) throws Exception {		
		user = loginService.createPass(userId, user);
		return ResponseEntity.ok(user);		
	}
}
