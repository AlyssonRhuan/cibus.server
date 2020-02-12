package com.arcs.cibus.server.resource;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.Produto;
import com.arcs.cibus.server.service.ProdutoService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/produto")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Produto>> getAll(int pagina, int qtdElementos) throws Exception {
		Page<Produto> produtos = produtoService.getAll(pagina - 1, qtdElementos);
		return ResponseEntity.ok(produtos);
	}
	
	@RequestMapping(value="/{produtoId}", method = RequestMethod.GET)
	public ResponseEntity<Produto> getById(@PathVariable Long produtoId) throws ObjectNotFoundException {
		Produto produto = produtoService.getById(produtoId);
		return ResponseEntity.ok(produto);
	}
	
	@RequestMapping(value="/{produtoId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable Long produtoId) throws ConstraintViolationException, Exception {
		produtoService.delete(produtoId);
		return ResponseEntity.ok(Boolean.TRUE);	
	}
	
	@RequestMapping(value="/{produtoId}", method = RequestMethod.PUT)
	public ResponseEntity<Produto> update(@PathVariable Long produtoId, @RequestBody Produto produto) throws Exception {
		produto.setProdutoID(produtoId);
		produto = produtoService.save(produto);
		return ResponseEntity.ok(produto);			
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Produto> insert(@RequestBody Produto produto) throws Exception {
		produto.setProdutoID(null);
		produto = produtoService.save(produto);
		return ResponseEntity.ok(produto);			
	}
}
