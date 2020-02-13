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

import com.arcs.cibus.server.domain.PerfilTelaPermissao;
import com.arcs.cibus.server.service.PerfilTelaPermissaoService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/perfiltelapermissao")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PerfilTelaPermissaoResource {
	
	@Autowired
	private PerfilTelaPermissaoService perfilTelaPermissaoService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PerfilTelaPermissao>> getAll() throws Exception {
		List<PerfilTelaPermissao> perfis = perfilTelaPermissaoService.getAll();
		return ResponseEntity.ok(perfis);
	}
	
	@RequestMapping(value="/{perfilTelaPermissaoId}", method = RequestMethod.GET)
	public ResponseEntity<PerfilTelaPermissao> getById(@PathVariable Long perfilTelaPermissaoId) throws ObjectNotFoundException {
		PerfilTelaPermissao perfil = perfilTelaPermissaoService.getById(perfilTelaPermissaoId);
		return ResponseEntity.ok(perfil);
	}
	
	@RequestMapping(value="/{perfilTelaPermissaoId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable Long perfilTelaPermissaoId) throws ConstraintViolationException, Exception {
		perfilTelaPermissaoService.delete(perfilTelaPermissaoId);
		return ResponseEntity.ok(Boolean.TRUE);	
	}
	
	@RequestMapping(value="/{perfilTelaPermissaoId}", method = RequestMethod.PUT)
	public ResponseEntity<PerfilTelaPermissao> update(@PathVariable Long perfilTelaPermissaoId, @RequestBody PerfilTelaPermissao perfil) throws Exception {
		perfil.setPerfilTelaPermissaoID(perfilTelaPermissaoId);
		perfil = perfilTelaPermissaoService.save(perfil);
		return ResponseEntity.ok(perfil);			
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PerfilTelaPermissao> insert(@RequestBody PerfilTelaPermissao perfil) throws Exception {
		perfil.setPerfilTelaPermissaoID(null);
		perfil = perfilTelaPermissaoService.save(perfil);
		return ResponseEntity.ok(perfil);		
	}
}
