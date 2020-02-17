package com.arcs.cibus.server.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.repository.ProductRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository produtoRepository;
	
	public Page<Product> getAll(int pagina, int qtdElementos) throws Exception {
		Pageable paginacao = PageRequest.of(pagina, qtdElementos);
		return produtoRepository.findAll(paginacao);
	}
	
	public Product getById(Long produtoId) throws ObjectNotFoundException {
		Optional<Product> produto = produtoRepository.findById(produtoId);		
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + produtoId + " não existe."));
	}
	
	public void delete(Long produtoId) throws ConstraintViolationException, Exception {	
		Product produto = this.getById(produtoId);
		produtoRepository.delete(produto);
	}
	
	public Product save(Product produto) throws Exception {
		return produtoRepository.save(produto); 
	}
}
