package com.arcs.cibus.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Produto;
import com.arcs.cibus.server.repository.ProdutoRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto findById(Long produtoId) {
		Optional<Produto> produto = produtoRepository.findById(produtoId);		
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Produto não encontrado! Id " + produtoId + " não existe."));
	}
}
