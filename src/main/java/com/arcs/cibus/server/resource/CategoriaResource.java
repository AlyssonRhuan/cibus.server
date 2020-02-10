package com.arcs.cibus.server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.Categoria;
import com.arcs.cibus.server.service.CategoriaService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Categoria>> getAll(int pagina, int qtdElementos) {
		Page<Categoria> categorias = categoriaService.getAll(pagina, qtdElementos);
		return ResponseEntity.ok(categorias);
	}
	
	@RequestMapping(value="/{categoriaId}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> findById(@PathVariable Long categoriaId) throws ObjectNotFoundException {
		Categoria categoria = categoriaService.findById(categoriaId);
		return ResponseEntity.ok(categoria);
	}
	
	@RequestMapping(value="/{categoriaId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long categoriaId) {
		try {
			categoriaService.delete(categoriaId);
			return ResponseEntity.ok(Boolean.TRUE.toString());		
		} 
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Categoria> update(Categoria categoria) {
		try {
			categoria = categoriaService.update(categoria);
			return ResponseEntity.ok(categoria);			
		} 
		catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Categoria> insert(Categoria categoria) {
		try {
			categoria = categoriaService.insert(categoria);
			return ResponseEntity.ok(categoria);			
		} 
		catch (Exception e) {
			return null;
		}
	}
}
	