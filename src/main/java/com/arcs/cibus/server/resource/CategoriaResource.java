package com.arcs.cibus.server.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.Categoria;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.service.CategoriaService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		return null;
	}
	
	@RequestMapping(value="/{categoriaId}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Long categoriaId) throws ObjectNotFoundException {
		Categoria categoria = categoriaService.findById(categoriaId);
		return ResponseEntity.ok(categoria);
	}
}
	