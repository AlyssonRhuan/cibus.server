package com.arcs.cibus.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Categoria;
import com.arcs.cibus.server.repository.CategoriaRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Long categoriaId) throws ObjectNotFoundException {
		Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);		
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + categoriaId + " não existe."));
	}
}
