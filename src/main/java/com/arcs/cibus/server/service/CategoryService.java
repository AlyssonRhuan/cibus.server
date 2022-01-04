package com.arcs.cibus.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.arcs.cibus.server.domain.enums.DomainActive;
import com.arcs.cibus.server.service.exceptions.DeleteException;
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
	
	public Page<Category> getAll(int pagina, int qtdElementos, String name, String description, DomainActive active) throws Exception {
		Pageable paginacao = PageRequest.of(pagina, qtdElementos);

		name = name.isEmpty() ? null : name.toLowerCase(Locale.ROOT);
		description = description.isEmpty() ? null : description.toLowerCase(Locale.ROOT);

		if(active.equals(DomainActive.BOUTH)){
			return categoriaRepository.findAll(paginacao, name, description);
		}
		else{
			boolean categoryActive = active.equals(DomainActive.YES) ? true : false;
			return categoriaRepository.findAll(paginacao, name, description, categoryActive);
		}
	}
	
	public Category getById(Long categoriaId) throws ObjectNotFoundException {
		Optional<Category> categoria = categoriaRepository.findById(categoriaId);		
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + categoriaId + " não existe."));
	}
	
	public void delete(Long categoriaId) throws ConstraintViolationException, Exception {	
		Category categoria = this.getById(categoriaId);
		if(categoria.getProducts().size() > 0){
			throw new DeleteException("A caegoria " + categoria.getName() + " não pode ser deletada, pois tem produtos.");
		}
		categoriaRepository.delete(categoria);
	}
	
	public Category save(Category categoria) throws Exception {
		return categoriaRepository.save(categoria); 
	}

	public List<Category> getAllValueLabel() {
		List<Category> categoriasValueLabel = categoriaRepository.findAll();
		List<Category> retorno = new ArrayList<>();

		for (Category categoria : categoriasValueLabel) {
			if(categoria.getActive().equals(Boolean.TRUE)){
				categoria.setTipoSerializer(TipoSerializer.VALUELABEL);
				retorno.add(categoria);
			}
		}
		
		return retorno;
	}
}
