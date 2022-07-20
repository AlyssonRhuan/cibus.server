package com.arcs.cibus.server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.service.MeService;
import com.arcs.cibus.server.service.UserService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/me")
public class MeResource {
	
	@Autowired
	private MeService meService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getById(@PathVariable Long userId) throws ObjectNotFoundException {
		User user = meService.getById(userId);
		user.setTipoSerializer(TipoSerializer.SIMPLE);
		return ResponseEntity.ok(user);
	}
	
	@RequestMapping(value="/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<User> update(@PathVariable Long userId, @RequestBody User user) throws Exception {
		User newUser = meService.getById(userId);
		newUser.setName(user.getName());
		newUser.setLogin(user.getLogin());
		user = userService.save(newUser);
		return ResponseEntity.ok(user);			
	}
}
