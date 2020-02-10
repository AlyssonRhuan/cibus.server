package com.arcs.cibus.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Categoria;
import com.arcs.cibus.server.repository.CategoriaRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Page<Categoria> getAll(int pagina, int qtdElementos) throws Exception {
		Pageable paginacao = PageRequest.of(pagina, qtdElementos);
		return categoriaRepository.findAll(paginacao);
	}
	
	public Categoria getById(Long categoriaId) throws ObjectNotFoundException {
		Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);		
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + categoriaId + " não existe."));
	}
	
	public void delete(Long categoriaId) throws Exception {	
		Categoria categoria = this.getById(categoriaId);
		categoriaRepository.delete(categoria);
	}
	
	public Categoria save(Categoria categoria) throws Exception {
		return categoriaRepository.save(categoria); 
	}
}
