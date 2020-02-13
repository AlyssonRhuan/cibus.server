package com.arcs.cibus.server.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.PerfilTelaPermissao;
import com.arcs.cibus.server.repository.PerfilTelaPermissaoRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class PerfilTelaPermissaoService {
	
	@Autowired
	private PerfilTelaPermissaoRepository perfilTelaPermissaoRepository;
	
	public List<PerfilTelaPermissao> getAll() throws Exception {
		return perfilTelaPermissaoRepository.findAll();
	}
	
	public PerfilTelaPermissao getById(Long perfilTelaPermissaoId) throws ObjectNotFoundException {
		Optional<PerfilTelaPermissao> perfilTelaPermissao = perfilTelaPermissaoRepository.findById(perfilTelaPermissaoId);		
		return perfilTelaPermissao.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + perfilTelaPermissaoId + " não existe."));
	}
	
	public void delete(Long perfilTelaPermissaoId) throws ConstraintViolationException, Exception {	
		PerfilTelaPermissao perfilTelaPermissao = this.getById(perfilTelaPermissaoId);
		perfilTelaPermissaoRepository.delete(perfilTelaPermissao);
	}
	
	public PerfilTelaPermissao save(PerfilTelaPermissao perfilTelaPermissao) throws Exception {
		return perfilTelaPermissaoRepository.save(perfilTelaPermissao); 
	}
}
