package com.arcs.cibus.server.resource;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.ProductSku;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.service.ProductService;
import com.arcs.cibus.server.service.ProductSkuService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/product/sku")
public class ProductSkuResource {
	
	@Autowired
	private ProductSkuService productSkuService;
	
	@Autowired 
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProductSku>> getAll(Long idProduct,int page, int quantity) throws Exception {
		Page<ProductSku> productsSku = productSkuService.getAll(idProduct, page - 1, quantity);
		return ResponseEntity.ok(productsSku);
	}
	
	@RequestMapping(value="/{productSkuId}", method = RequestMethod.GET)
	public ResponseEntity<ProductSku> getById(@PathVariable Long productSkuId) throws ObjectNotFoundException {
		ProductSku productSku = productSkuService.getById(productSkuId);
		productSku.setTipoSerializer(TipoSerializer.FULL);
		return ResponseEntity.ok(productSku);
	}
	
	@RequestMapping(value="/{productSkuId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable Long productSkuId) throws ConstraintViolationException, Exception {
		productSkuService.delete(productSkuId);
		return ResponseEntity.ok(Boolean.TRUE);	
	}
	
	@RequestMapping(value="/{productSkuId}", method = RequestMethod.PUT)
	public ResponseEntity<ProductSku> update(@PathVariable Long productSkuId, @RequestBody ProductSku productSku) throws Exception {
		productSku.setId(productSkuId);
		productSku = productSkuService.save(productSku);
		return ResponseEntity.ok(productSku);			
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ProductSku> insert(@RequestBody ProductSku productSku) throws Exception {
		productSku.setId(null);
		
		Product product = productService.getById(productSku.getProduct().getId());
		if(product.isDigital()){
			productSku.setStockQuantity(new Double(0));
		}
		
		productSku = productSkuService.save(productSku);
		return ResponseEntity.ok(productSku);			
	}
}
