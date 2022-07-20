package com.arcs.cibus.server.service;

import java.util.Locale;
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
import org.springframework.util.StringUtils;

@Service
public class ProductService {

	@Autowired
	private ProductRepository produtoRepository;

	@Autowired
	private ImageService imageService;
	
	public Page<Product> getAll(int page, int qtdElementos, String name, Long categoryId, DomainActive active) throws Exception {
		Pageable paginacao = PageRequest.of(page, qtdElementos);

		name = name.isEmpty() ? null : name.toLowerCase(Locale.ROOT);
		if(categoryId.equals(0L)) categoryId = null;

		Page<Product> productsPageable;

		if(active.equals(DomainActive.BOUTH)){
			productsPageable = produtoRepository.findAll(paginacao, name, categoryId);
		}
		else{
			boolean categoryActive = active.equals(DomainActive.YES) ? true : false;
			productsPageable = produtoRepository.findAll(paginacao, name, categoryId, categoryActive);
		}

		productsPageable.get().forEach(product -> {
			String productImagePath = product.getImagePath();
			if(!StringUtils.isEmpty(productImagePath)) {
				String productImage = imageService.get(productImagePath);
				product.setImage(productImage);
			}
		});

		return productsPageable;
	}
	
	public Product getById(Long productId) throws ObjectNotFoundException {
		Optional<Product> product = produtoRepository.findById(productId);

		if(!StringUtils.isEmpty(product.get().getImagePath())) {
			product.get().setImage(imageService.get(product.get().getImagePath()));
		}

		product.get().setImage(imageService.get(product.get().getImagePath()));
		return product.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + productId + " não existe."));
	}
	
	public void delete(Long produtoId) throws ConstraintViolationException, Exception {	
		Product produto = this.getById(produtoId);
		produto.setIsActive(Boolean.FALSE);
		produtoRepository.save(produto);
	}
	
	public Product save(Product produto) throws Exception {
		if(!StringUtils.isEmpty(produto.getImage())) {
			String imagePath = imageService.save(produto.getImage(), produto.getName() + ".png");
			produto.setImagePath(imagePath);
		}
		return produtoRepository.save(produto); 
	}

    public Page<Product> getAllByCategory(int pagina, int qtdElementos, Long categoryId) throws Exception {
		if(categoryId.equals(0L)){
			categoryId = null;
		}

		Pageable paginacao = PageRequest.of(pagina, qtdElementos);
		Page<Product> productsPageable = produtoRepository.getAllByCategory(paginacao, categoryId);

		productsPageable.get().forEach(product -> {
			String productImagePath = product.getImagePath();
			if(!StringUtils.isEmpty(productImagePath)) {
				String productImage = imageService.get(productImagePath);
				product.setImage(productImage);
			}
		});

		return productsPageable;
    }
}
