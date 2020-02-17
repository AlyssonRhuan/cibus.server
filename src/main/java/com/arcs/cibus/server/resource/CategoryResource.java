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

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.service.CategoryService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryResource {
	
	@Autowired
	private CategoryService categoriaService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Category>> getAll(int page, int quantity) throws Exception {
		Page<Category> categorias = categoriaService.getAll(page - 1, quantity);
		return ResponseEntity.ok(categorias);
	}
	
	@RequestMapping(value="/{categoriaId}", method = RequestMethod.GET)
	public ResponseEntity<Category> getById(@PathVariable Long categoriaId) throws ObjectNotFoundException {
		Category categoria = categoriaService.getById(categoriaId);
		return ResponseEntity.ok(categoria);
	}
	
	@RequestMapping(value="/{categoriaId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable Long categoriaId) throws ConstraintViolationException, Exception {
		categoriaService.delete(categoriaId);
		return ResponseEntity.ok(Boolean.TRUE);	
	}
	
	@RequestMapping(value="/{categoriaId}", method = RequestMethod.PUT)
	public ResponseEntity<Category> update(@PathVariable Long categoriaId, @RequestBody Category categoria) throws Exception {
		categoria.setId(categoriaId);
		categoria = categoriaService.save(categoria);
		return ResponseEntity.ok(categoria);			
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Category> insert(@RequestBody Category categoria) throws Exception {
		categoria.setId(null);
		categoria = categoriaService.save(categoria);
		return ResponseEntity.ok(categoria);			
	}
		
	@RequestMapping(value="/valuelabel", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getAllValueLabel() throws Exception {
		List<Category> categorias = categoriaService.getAllValueLabel();
		return ResponseEntity.ok(categorias);
	}
}
	