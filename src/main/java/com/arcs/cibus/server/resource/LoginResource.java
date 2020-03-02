package com.arcs.cibus.server.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.security.JWTUtil;

@RestController
@RequestMapping(value = "/login")
public class LoginResource {
	
	@Autowired
	private JWTUtil jwtUtil;	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Boolean> isAtivo(HttpServletRequest request) throws Exception {

		String token = request.getHeader("Authorization");
		
		if(token != null && token.startsWith("Bearer ")){
			Boolean isTokenValid = jwtUtil.isTokenValid(token);
			return ResponseEntity.ok(isTokenValid);
		}
		
		return ResponseEntity.ok(false);
	}
}
