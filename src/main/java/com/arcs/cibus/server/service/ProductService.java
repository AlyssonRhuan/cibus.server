package com.arcs.cibus.server.service;

import java.util.Optional;

import com.arcs.cibus.server.domain.enums.DomainActive;
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
	
	public Page<Product> getAll(int pagina, int qtdElementos, String name, Long categoryId, DomainActive active) throws Exception {
		Pageable paginacao = PageRequest.of(pagina, qtdElementos);


		if(name.isEmpty()) name = null;
		if(categoryId.equals(0L)) categoryId = null;

		if(active.equals(DomainActive.BOUTH)){
			return produtoRepository.findAll(paginacao, name, categoryId);
		}
		else{
			boolean categoryActive = active.equals(DomainActive.YES) ? true : false;
			return produtoRepository.findAll(paginacao, name, categoryId, categoryActive);
		}
	}
	
	public Product getById(Long productId) throws ObjectNotFoundException {
		Optional<Product> product = produtoRepository.findById(productId);
		return product.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + productId + " não existe."));
	}
	
	public void delete(Long produtoId) throws ConstraintViolationException, Exception {	
		Product produto = this.getById(produtoId);
		produtoRepository.delete(produto);
	}
	
	public Product save(Product produto) throws Exception {
		return produtoRepository.save(produto); 
	}

    public Page<Product> getAllByCategory(int pagina, int qtdElementos, Long categoryId) throws Exception {
		if(categoryId.equals(0L)){
			categoryId = null;
		}
		Pageable paginacao = PageRequest.of(pagina, qtdElementos);
		return produtoRepository.getAllByCategory(paginacao, categoryId);
    }
}
