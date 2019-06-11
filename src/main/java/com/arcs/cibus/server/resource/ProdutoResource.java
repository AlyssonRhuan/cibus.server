package com.arcs.cibus.server.resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.Produto;
import com.arcs.cibus.server.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Produto> listar() {
		return null;
	}
	
	@RequestMapping(value="/{produtoId}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Long produtoId) {
		Produto produto = produtoService.findById(produtoId);
		return ResponseEntity.ok(produto);
	}
}
