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
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product, createdProduct);
        verify(productRepository).create(product);
    }

    @Test
    void testDelete() {
        String id = "1";
        productService.delete(id);

        verify(productRepository).delete(id);
    }

    @Test
    void testEdit() {
        Product product = new Product();
        productService.edit(product);

        verify(productRepository).edit(product);
    }

    @Test
    void testFindById() {
        String id = "1";
        Product product = new Product();
        when(productRepository.findById(id)).thenReturn(product);

        Product foundProduct = productService.findById(id);

        assertEquals(product, foundProduct);
        verify(productRepository).findById(id);
    }

    @Test
    void testFindAllProduct() {
        List<Product> productList = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("123");
        product1.setProductName("Kangkung");
        product1.setProductQuantity(1);
        productList.add(product1);
        Product product2 = new Product();
        product2.setProductId("321");
        product2.setProductName("Bayam");
        product2.setProductQuantity(2);
        productList.add(product2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertEquals(productList.size(), result.size());
        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), result.get(i));
        }

        verify(productRepository, times(1)).findAll();
    }
}