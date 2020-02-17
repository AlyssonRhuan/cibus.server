package com.arcs.cibus.server.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.repository.UserRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class UserService {
	
	@Autowired
	private UserRepository usuarioRepository;
	
	public Page<User> getAll(int pagina, int qtdElementos) throws Exception {
		Pageable paginacao = PageRequest.of(pagina, qtdElementos);
		return usuarioRepository.findAll(paginacao);
	}
	
	public User getById(Long usuarioId) throws ObjectNotFoundException {
		Optional<User> usuario = usuarioRepository.findById(usuarioId);		
		return usuario.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + usuarioId + " não existe."));
	}
	
	public void delete(Long usuarioId) throws ConstraintViolationException, Exception {	
		User usuario = this.getById(usuarioId);
		usuarioRepository.delete(usuario);
	}
	
	public User save(User usuario) throws Exception {
		return usuarioRepository.save(usuario); 
	}
}