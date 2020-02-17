package com.arcs.cibus.server.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.View;
import com.arcs.cibus.server.repository.ViewRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class ViewService {
	
	@Autowired
	private ViewRepository telaRepository;
	
	public List<View> getAll() throws Exception {
		return telaRepository.findAll();
	}
	
	public View getById(Long telaId) throws ObjectNotFoundException {
		Optional<View> tela = telaRepository.findById(telaId);		
		return tela.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + telaId + " não existe."));
	}
	
	public void delete(Long telaId) throws ConstraintViolationException, Exception {	
		View tela = this.getById(telaId);
		telaRepository.delete(tela);
	}
	
	public View save(View tela) throws Exception {
		return telaRepository.save(tela); 
	}
}
