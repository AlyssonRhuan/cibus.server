package com.arcs.cibus.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.repository.UserRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class MeService {
	
	@Autowired
	private UserRepository usuarioRepository;
	
	public User getById(Long usuarioId) throws ObjectNotFoundException {
		Optional<User> usuario = usuarioRepository.findById(usuarioId);
		return usuario.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + usuarioId + " não existe."));
	}	
}
