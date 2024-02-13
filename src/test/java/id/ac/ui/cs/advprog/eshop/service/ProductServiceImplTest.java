package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {
        Product product = new Product();

        when(productRepository.create(product)).thenReturn(product);

        Product result = productService.create(product);

        assertEquals(product, result);
        verify(productRepository).create(product);
    }

    @Test
    void delete() {
        String id = "123";

        productService.delete(id);

        verify(productRepository).delete(id);
    }

    @Test
    void edit() {
        Product product = new Product();

        productService.edit(product);

        verify(productRepository).edit(product);
    }

    @Test
    void findById() {
        String id = "123";
        Product product = new Product();

        when(productRepository.findById(id)).thenReturn(product);

        Product result = productService.findById(id);

        assertEquals(product, result);
        verify(productRepository).findById(id);
    }

    @Test
    void findAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertEquals(productList.size(), result.size());
        verify(productRepository).findAll();
    }
}
