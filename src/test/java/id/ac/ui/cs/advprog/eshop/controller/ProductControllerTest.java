package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHomePage() {
        assertEquals("homePage", productController.homePage());
    }

    @Test
    void testCreateProductPage() {
        assertEquals("createProduct", productController.createProductPage(model));
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        assertEquals("redirect:list", productController.createProductPost(product, model));
        verify(productService).create(product);
    }

    @Test
    void testDeleteProduct() {
        String id = "1";
        assertEquals("redirect:../list", productController.deleteProduct(id, model));
        verify(productService).delete(id);
    }

    @Test
    void testEditProductPage() {
        String id = "1";
        Product product = new Product();
        when(productService.findById(id)).thenReturn(product);

        assertEquals("editProduct", productController.editProductPage(id, model));
        verify(model).addAttribute(eq("product"), eq(product));
    }

    @Test
    void testEditProductPost() {
        String id = "1";
        Product product = new Product();
        assertEquals("redirect:../list", productController.editProductPost(id, product, model));
        verify(productService).edit(product);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);

        assertEquals("productList", productController.productListPage(model));
        verify(model).addAttribute(eq("products"), eq(productList));
    }
}
