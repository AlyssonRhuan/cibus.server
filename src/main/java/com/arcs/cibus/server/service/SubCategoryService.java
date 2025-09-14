package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.SubCategory;
import com.arcs.cibus.server.domain.enums.DomainActive;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.repository.SubCategoryRepository;
import com.arcs.cibus.server.service.exceptions.DeleteException;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class SubCategoryService {
	
	@Autowired
	private SubCategoryRepository subCategoriaRepository;
	
	public Page<SubCategory> getAll(int pagina, int qtdElementos, String name, String subCategory, DomainActive active) throws Exception {
		Pageable paginacao = PageRequest.of(pagina, qtdElementos);

		name = name.isEmpty() ? null : name.toLowerCase(Locale.ROOT);
		subCategory = subCategory.isEmpty() ? null : subCategory.toLowerCase(Locale.ROOT);

		if(active.equals(DomainActive.BOUTH)){
			return subCategoriaRepository.findAll(paginacao, name, subCategory);
		}
		else{
			boolean categoryActive = active.equals(DomainActive.YES);
			return subCategoriaRepository.findAll(paginacao, name, subCategory, categoryActive);
		}
	}
	
	public SubCategory getById(Long subCategoryId) throws ObjectNotFoundException {
		Optional<SubCategory> categoria = subCategoriaRepository.findById(subCategoryId);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Subcategoria não encontrada! Id " + subCategoryId + " não existe."));
	}
	
	public void delete(Long subCategoryId) throws ConstraintViolationException, Exception {
		SubCategory subCategory = this.getById(subCategoryId);
		if(subCategory.getProducts().size() > 0){
			throw new DeleteException("A subcategoria " + subCategory.getName() + " não pode ser deletada, pois tem produtos.");
		}
		subCategoriaRepository.delete(subCategory);
	}
	
	public SubCategory save(SubCategory subCategory) throws Exception {
		return subCategoriaRepository.save(subCategory);
	}

	public List<SubCategory> getAllValueLabel() {
		List<SubCategory> subcategoriasValueLabel = subCategoriaRepository.findAll();
		List<SubCategory> retorno = new ArrayList<>();

		for (SubCategory subCategory : subcategoriasValueLabel) {
			if(subCategory.getActive().equals(Boolean.TRUE)){
				subCategory.setTipoSerializer(TipoSerializer.VALUELABEL);
				retorno.add(subCategory);
			}
		}
		
		return retorno;
	}
}
