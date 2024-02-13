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

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void homePage() {
        String result = productController.homePage();
        assertEquals("homePage", result);
    }

    @Test
    void createProductPage() {
        Model model = mock(Model.class);

        String result = productController.createProductPage(model);

        assertEquals("createProduct", result);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void createProductPost() {
        Product product = new Product();

        String result = productController.createProductPost(product, mock(Model.class));

        assertEquals("redirect:list", result);
        verify(productService).create(product);
    }

    @Test
    void deleteProduct() {
        String id = "123";

        String result = productController.deleteProduct(id, mock(Model.class));

        assertEquals("redirect:../list", result);
        verify(productService).delete(id);
    }

    @Test
    void editProductPage() {
        String id = "123";
        Product product = new Product();
        when(productService.findById(id)).thenReturn(product);
        Model model = mock(Model.class);

        String result = productController.editProductPage(id, model);

        assertEquals("editProduct", result);
        verify(model).addAttribute(eq("product"), eq(product));
    }

    @Test
    void editProductPost() {
        String id = "123";
        Product product = new Product();
        product.setProductId(id);

        String result = productController.editProductPost(id, product, mock(Model.class));

        assertEquals("redirect:../list", result);
        verify(productService).edit(product);
    }

    @Test
    void productListPage() {
        List<Product> products = new ArrayList<>();
        when(productService.findAll()).thenReturn(products);
        Model model = mock(Model.class);

        String result = productController.productListPage(model);

        assertEquals("productList", result);
        verify(model).addAttribute(eq("products"), eq(products));
    }
}
