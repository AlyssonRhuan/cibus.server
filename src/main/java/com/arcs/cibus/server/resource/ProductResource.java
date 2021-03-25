package com.arcs.cibus.server.resource;

import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.enums.DomainActive;
import com.arcs.cibus.server.service.ProductService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductResource {

    @Autowired
    private ProductService produtoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Product>> getAll(int page, int quantity, String name, Long categoryId, DomainActive active) throws Exception {
        Page<Product> produtos = produtoService.getAll(page - 1, quantity, name, categoryId, active);
        return ResponseEntity.ok(produtos);
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<Page<Product>> getAllByCategory(int page, int quantity, @PathVariable Long categoryId) throws Exception {
        Page<Product> produtos = produtoService.getAllByCategory(page - 1, quantity, categoryId);
        return ResponseEntity.ok(produtos);
    }

    @RequestMapping(value = "/{produtoId}", method = RequestMethod.GET)
    public ResponseEntity<Product> getById(@PathVariable Long produtoId) throws ObjectNotFoundException {
        Product produto = produtoService.getById(produtoId);
        return ResponseEntity.ok(produto);
    }

    @RequestMapping(value = "/{produtoId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable Long produtoId) throws ConstraintViolationException, Exception {
        produtoService.delete(produtoId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @RequestMapping(value = "/{produtoId}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(@PathVariable Long produtoId, @RequestBody Product produto) throws Exception {
        produto.setId(produtoId);
        produto = produtoService.save(produto);
        return ResponseEntity.ok(produto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> insert(@RequestBody Product product) throws Exception {
        product.setId(null);
        product = produtoService.save(product);
        return ResponseEntity.ok(product);
    }
}
