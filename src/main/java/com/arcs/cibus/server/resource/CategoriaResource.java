package com.arcs.cibus.server.resource;

import java.util.List;

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

import com.arcs.cibus.server.domain.Categoria;
import com.arcs.cibus.server.service.CategoriaService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/categorias")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Categoria>> getAll(int pagina, int qtdElementos) throws Exception {
		Page<Categoria> categorias = categoriaService.getAll(pagina - 1, qtdElementos);
		return ResponseEntity.ok(categorias);
	}
	
	@RequestMapping(value="/{categoriaId}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> getById(@PathVariable Long categoriaId) throws ObjectNotFoundException {
		Categoria categoria = categoriaService.getById(categoriaId);
		return ResponseEntity.ok(categoria);
	}
	
	@RequestMapping(value="/{categoriaId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable Long categoriaId) throws ConstraintViolationException, Exception {
		categoriaService.delete(categoriaId);
		return ResponseEntity.ok(Boolean.TRUE);	
	}
	
	@RequestMapping(value="/{categoriaId}", method = RequestMethod.PUT)
	public ResponseEntity<Categoria> update(@PathVariable Long categoriaId, @RequestBody Categoria categoria) throws Exception {
		categoria.setCategoriaID(categoriaId);
		categoria = categoriaService.save(categoria);
		return ResponseEntity.ok(categoria);			
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Categoria> insert(@RequestBody Categoria categoria) throws Exception {
		categoria.setCategoriaID(null);
		categoria = categoriaService.save(categoria);
		return ResponseEntity.ok(categoria);			
	}
		
	@RequestMapping(value="/valuelabel", method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> getAllValueLabel() throws Exception {
		List<Categoria> categorias = categoriaService.getAllValueLabel();
		return ResponseEntity.ok(categorias);
	}
}
	