package com.arcs.cibus.server.resource;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.Perfil;
import com.arcs.cibus.server.service.PerfilService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/perfil")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PerfilResource {
	
	@Autowired
	private PerfilService perfilService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Perfil>> getAll(int pagina, int qtdElementos) throws Exception {
		Page<Perfil> perfis = perfilService.getAll(pagina - 1, qtdElementos);
		return ResponseEntity.ok(perfis);
	}
	
	@RequestMapping(value="/{perfilId}", method = RequestMethod.GET)
	public ResponseEntity<Perfil> getById(@PathVariable Long perfilId) throws ObjectNotFoundException {
		Perfil perfil = perfilService.getById(perfilId);
		return ResponseEntity.ok(perfil);
	}
	
	@RequestMapping(value="/{perfilId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable Long perfilId) throws ConstraintViolationException, Exception {
		perfilService.delete(perfilId);
		return ResponseEntity.ok(Boolean.TRUE);	
	}
	
	@RequestMapping(value="/{perfilId}", method = RequestMethod.PUT)
	public ResponseEntity<Perfil> update(@PathVariable Long perfilId, @RequestBody Perfil perfil) throws Exception {
		perfil.setId(perfilId);
		perfil = perfilService.save(perfil);
		return ResponseEntity.ok(perfil);			
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Perfil> insert(@RequestBody Perfil perfil) throws Exception {
		perfil = perfilService.save(perfil);		
		return ResponseEntity.ok(perfil);		
	}
}
