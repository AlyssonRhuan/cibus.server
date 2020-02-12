package com.arcs.cibus.server.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Tela;
import com.arcs.cibus.server.repository.TelaRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class TelaService {
	
	@Autowired
	private TelaRepository telaRepository;
	
	public List<Tela> getAll() throws Exception {
		return telaRepository.findAll();
	}
	
	public Tela getById(Long telaId) throws ObjectNotFoundException {
		Optional<Tela> tela = telaRepository.findById(telaId);		
		return tela.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + telaId + " não existe."));
	}
	
	public void delete(Long telaId) throws ConstraintViolationException, Exception {	
		Tela tela = this.getById(telaId);
		telaRepository.delete(tela);
	}
	
	public Tela save(Tela tela) throws Exception {
		return telaRepository.save(tela); 
	}
}
