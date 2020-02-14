package com.arcs.cibus.server.resource;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.Tela;
import com.arcs.cibus.server.service.TelaService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/tela")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TelaResource {
	
	@Autowired
	private TelaService telaService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Tela>> getAll() throws Exception {
		List<Tela> telas = telaService.getAll();
		return ResponseEntity.ok(telas);
	}
	
	@RequestMapping(value="/{telaId}", method = RequestMethod.GET)
	public ResponseEntity<Tela> getById(@PathVariable Long telaId) throws ObjectNotFoundException {
		Tela tela = telaService.getById(telaId);
		return ResponseEntity.ok(tela);
	}
	
	@RequestMapping(value="/{telaId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable Long telaId) throws ConstraintViolationException, Exception {
		telaService.delete(telaId);
		return ResponseEntity.ok(Boolean.TRUE);	
	}
	
	@RequestMapping(value="/{telaId}", method = RequestMethod.PUT)
	public ResponseEntity<Tela> update(@PathVariable Long telaId, @RequestBody Tela tela) throws Exception {
		tela.setId(telaId);
		tela = telaService.save(tela);
		return ResponseEntity.ok(tela);			
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Tela> insert(@RequestBody Tela tela) throws Exception {
		tela.setId(null);
		tela = telaService.save(tela);
		return ResponseEntity.ok(tela);			
	}
}
