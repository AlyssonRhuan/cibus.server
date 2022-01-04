package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.enums.DomainActive;
import com.arcs.cibus.server.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private static long PRODUCT_ID_VALID = 1l;
    private static long PRODUCT_ID_INVALID = 0l;
    private static String PRODUCT_NAME = "productName";
    private static Product productToBeReturned;

    @Mock
    private ProductRepository produtoRepository;

    @Autowired
    private ProductService classUnderTest;

    Pageable pageable;

    @Before
    public void setup(){
        productToBeReturned = Product.builder()
                .id(PRODUCT_ID_VALID)
                .name(PRODUCT_NAME)
                .build();
    }

    @Test
    public void getById_withValidId_shouldReturnValidProduct() throws Exception {
        when(produtoRepository.findById(PRODUCT_ID_VALID)).thenReturn(Optional.of(productToBeReturned));
        Product classUnderTestReturn = classUnderTest.getById(PRODUCT_ID_VALID);

        assertThat(classUnderTestReturn.getName(), equals(PRODUCT_NAME));
    }
}
