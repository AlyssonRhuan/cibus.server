package com.arcs.cibus.server.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.View;
import com.arcs.cibus.server.repository.ViewRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class ViewService {
	
	@Autowired
	private ViewRepository telaRepository;
	
	public List<View> getAll(User user) throws Exception {
		/**
		 * LEVEL 1 - FOR ADMIN USER (ALL ACCESS, LEVEL 1, 2 AND 3)
		 * LEVEL 2 - FOR SALESMAN USER (ACCESS VIEW FROM LEVEL 2 AND LEVEL 3)
		 * LEVEL 3 - FOR CLIENT USER (ACCESS VIEW FROM LEVEL 3)
		 */
		if(user.isProfileSalesman()){
			return telaRepository.findAll().stream().filter(v -> v.getLevel() != 1).collect(Collectors.toList());			
		}	
		if(user.isProfileClient()){
			return telaRepository.findAll().stream().filter(v -> v.getLevel().equals(3)).collect(Collectors.toList());			
		}	

		// FOR ADMIN
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
