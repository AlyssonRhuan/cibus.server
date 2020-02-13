package com.arcs.cibus.server.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Perfil;
import com.arcs.cibus.server.repository.PerfilRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public Page<Perfil> getAll(int pagina, int qtdElementos) throws Exception {
		Pageable paginacao = PageRequest.of(pagina, qtdElementos);
		return perfilRepository.findAll(paginacao);
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
