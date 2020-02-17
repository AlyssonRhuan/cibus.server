package com.arcs.cibus.server.resource;

import java.util.List;

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

import com.arcs.cibus.server.domain.Profile;
import com.arcs.cibus.server.service.ProfileService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/profile")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProfileResource {
	
	@Autowired
	private ProfileService profileService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Profile>> getAll(int page, int quantity) throws Exception {
		Page<Profile> perfis = profileService.getAll(page - 1, quantity);
		return ResponseEntity.ok(perfis);
	}
	
	@RequestMapping(value="/{perfilId}", method = RequestMethod.GET)
	public ResponseEntity<Profile> getById(@PathVariable Long perfilId) throws ObjectNotFoundException {
		Profile perfil = profileService.getById(perfilId);
		return ResponseEntity.ok(perfil);
	}
	
	@RequestMapping(value="/{perfilId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable Long perfilId) throws ConstraintViolationException, Exception {
		profileService.delete(perfilId);
		return ResponseEntity.ok(Boolean.TRUE);	
	}
	
	@RequestMapping(value="/{perfilId}", method = RequestMethod.PUT)
	public ResponseEntity<Profile> update(@PathVariable Long perfilId, @RequestBody Profile perfil) throws Exception {
		perfil.setId(perfilId);
		perfil = profileService.save(perfil);
		return ResponseEntity.ok(perfil);			
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Profile> insert(@RequestBody Profile perfil) throws Exception {
		perfil = profileService.save(perfil);		
		return ResponseEntity.ok(perfil);		
	}
	
	@RequestMapping(value="/valuelabel", method = RequestMethod.GET)
	public ResponseEntity<List<Profile>> getAllValueLabel() throws Exception {
		List<Profile> profiles = profileService.getAllValueLabel();
		return ResponseEntity.ok(profiles);
	}
}
