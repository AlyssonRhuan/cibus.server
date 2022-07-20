package com.arcs.cibus.server.resource;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.service.UserService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<User>> getAll(int page, int quantity, String name, String login) throws Exception {
		Page<User> usuarios = userService.getAll(page - 1, quantity, name, login);
		return ResponseEntity.ok(usuarios);
	}

	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{usuarioId}", method = RequestMethod.GET)
	public ResponseEntity<User> getById(@PathVariable Long usuarioId) throws ObjectNotFoundException {
		User usuario = userService.getById(usuarioId);
		return ResponseEntity.ok(usuario);
	}

	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{usuarioId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable Long usuarioId) throws ConstraintViolationException, Exception {
		userService.delete(usuarioId);
		return ResponseEntity.ok(Boolean.TRUE);	
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{usuarioId}", method = RequestMethod.PUT)
	public ResponseEntity<User> update(@PathVariable Long usuarioId, @RequestBody User usuario) throws Exception {
		usuario.setId(usuarioId);
		usuario = userService.save(usuario);
		return ResponseEntity.ok(usuario);			
	}

	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> insert(@RequestBody User user) throws Exception {
		user.setId(null);
		user.setPass(passwordEncoder.encode(user.getPass()));

		user = userService.save(user);

		return ResponseEntity.ok(user);
	}
}
