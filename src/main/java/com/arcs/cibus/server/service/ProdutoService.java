package com.arcs.cibus.server.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Produto;
import com.arcs.cibus.server.repository.ProdutoRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Page<Produto> getAll(int pagina, int qtdElementos) throws Exception {
		Pageable paginacao = PageRequest.of(pagina, qtdElementos);
		return produtoRepository.findAll(paginacao);
	}
	
	public Produto getById(Long produtoId) throws ObjectNotFoundException {
		Optional<Produto> produto = produtoRepository.findById(produtoId);		
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + produtoId + " não existe."));
	}
	
	public void delete(Long produtoId) throws ConstraintViolationException, Exception {	
		Produto produto = this.getById(produtoId);
		produtoRepository.delete(produto);
	}
	
	public Produto save(Produto produto) throws Exception {
		return produtoRepository.save(produto); 
	}
}
