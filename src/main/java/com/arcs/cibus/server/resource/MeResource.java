package com.arcs.cibus.server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.service.MeService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/me")
public class MeResource {
	
	@Autowired
	private MeService meService;
	
	@RequestMapping(value="/{usuarioId}", method = RequestMethod.GET)
	public ResponseEntity<User> getById(@PathVariable Long usuarioId) throws ObjectNotFoundException {
		User usuario = meService.getById(usuarioId);
		return ResponseEntity.ok(usuario);
	}
}
