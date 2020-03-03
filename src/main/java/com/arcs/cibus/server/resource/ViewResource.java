package com.arcs.cibus.server.resource;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.View;
import com.arcs.cibus.server.service.UserService;
import com.arcs.cibus.server.service.ViewService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/view")
public class ViewResource {
	
	@Autowired
	private ViewService telaService;
	
	@Autowired	
	private UserService userService;

	@RequestMapping(value="/user/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<View>> getAll(@PathVariable Long userId) throws Exception {
		User user = userService.getById(userId);		
		List<View> telas = telaService.getAll(user);
		return ResponseEntity.ok(telas);
	}
	
	@RequestMapping(value="/{telaId}", method = RequestMethod.GET)
	public ResponseEntity<View> getById(@PathVariable Long telaId) throws ObjectNotFoundException {
		View tela = telaService.getById(telaId);
		return ResponseEntity.ok(tela);
	}
	
	@RequestMapping(value="/{telaId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable Long telaId) throws ConstraintViolationException, Exception {
		telaService.delete(telaId);
		return ResponseEntity.ok(Boolean.TRUE);	
	}
	
	@RequestMapping(value="/{telaId}", method = RequestMethod.PUT)
	public ResponseEntity<View> update(@PathVariable Long telaId, @RequestBody View tela) throws Exception {
		tela.setId(telaId);
		tela = telaService.save(tela);
		return ResponseEntity.ok(tela);			
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<View> insert(@RequestBody View tela) throws Exception {
		tela.setId(null);
		tela = telaService.save(tela);
		return ResponseEntity.ok(tela);			
	}
}
