package com.arcs.cibus.server.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Perfil;
import com.arcs.cibus.server.repository.PerfilRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public List<Perfil> getAll() throws Exception {
		return perfilRepository.findAll();
	}
	
	public Perfil getById(Long perfilId) throws ObjectNotFoundException {
		Optional<Perfil> perfil = perfilRepository.findById(perfilId);		
		return perfil.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + perfilId + " não existe."));
	}
	
	public void delete(Long perfilId) throws ConstraintViolationException, Exception {	
		Perfil perfil = this.getById(perfilId);
		perfilRepository.delete(perfil);
	}
	
	public Perfil save(Perfil perfil) throws Exception {
		return perfilRepository.save(perfil); 
	}
}
