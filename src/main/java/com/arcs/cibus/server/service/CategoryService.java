package com.arcs.cibus.server.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.repository.CategoryRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoriaRepository;
	
	public Page<Category> getAll(int pagina, int qtdElementos) throws Exception {
		Pageable paginacao = PageRequest.of(pagina, qtdElementos);
		return categoriaRepository.findAll(paginacao);
	}
	
	public Category getById(Long categoriaId) throws ObjectNotFoundException {
		Optional<Category> categoria = categoriaRepository.findById(categoriaId);		
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + categoriaId + " não existe."));
	}
	
	public void delete(Long categoriaId) throws ConstraintViolationException, Exception {	
		Category categoria = this.getById(categoriaId);
		categoriaRepository.delete(categoria);
	}
	
	public Category save(Category categoria) throws Exception {
		return categoriaRepository.save(categoria); 
	}

	public List<Category> getAllValueLabel() {
		List<Category> categoriasValueLabel = categoriaRepository.findAll();
		
		for (Category categoria : categoriasValueLabel) {
			categoria.setTipoSerializer(TipoSerializer.VALUELABEL);
		}
		
		return categoriasValueLabel;
	}
}
