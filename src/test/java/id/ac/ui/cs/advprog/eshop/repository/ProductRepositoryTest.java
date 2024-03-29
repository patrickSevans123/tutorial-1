package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setup() {
    }

    @Test
    void testCreateAndFind() {

        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());

    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());

    }

    @Test
    void testSuccessDeleteProduct() {
        Product product1 = new Product();
        product1.setProductId("123");
        product1.setProductName("Cah Kangkung");
        product1.setProductQuantity(1);
        productRepository.create(product1);

        productRepository.delete("123");

        Iterator<Product> productIterator = productRepository.findAll();
        while (productIterator.hasNext()){
            assertNotEquals(productIterator.next().getProductId(), "abcde");
        }
    }

    @Test
    void testFailedDeleteProduct() {
        Product product1 = new Product();
        product1.setProductName("Lontong");
        product1.setProductId("123");
        product1.setProductQuantity(1);
        productRepository.create(product1);

        productRepository.delete("124");
        assertNotNull(productRepository.findById(product1.getProductId()));
    }

    @Test
    void testSuccessEditProduct() {
        Product product1 = new Product();
        product1.setProductId("123");
        product1.setProductName("Cah Kangkung");
        product1.setProductQuantity(1);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId(product1.getProductId());
        product2.setProductName("Ayam Bakar");
        product2.setProductQuantity(2);

        productRepository.edit(product2);

        Product findedProduct = productRepository.findById(product1.getProductId());

        // Produk dengan ID 123 telah berubah namanya menjadi ayam bakar
        assertEquals(findedProduct.getProductName(), "Ayam Bakar");
    }

    @Test
    void testFailedEditProduct() {
        Product product1 = new Product();
        product1.setProductId("123");
        product1.setProductName("Cah Kangkung");
        product1.setProductQuantity(1);
        productRepository.create(product1);

        //Edit produk yang idnya tidak ada di program
        Product product2 = new Product();
        product2.setProductId("id ngasal");
        product2.setProductName("Ayam Bakar");
        product2.setProductQuantity(2);

        productRepository.edit(product2);

        //Mencari produk dengan id 123
        Product findedProduct = productRepository.findById(product1.getProductId());

        //Produk dengan id 123 tidak berubah namanya karena program tidak berhasil mencari produk dengan id ngasal
        assertEquals(findedProduct.getProductName(), "Cah Kangkung");
    }

    @Test
    void testFindByIdReturnProduct() {
        Product product1 = new Product();
        product1.setProductId("123");
        product1.setProductName("Cah Kangkung");
        product1.setProductQuantity(1);
        productRepository.create(product1);

        Product foundProduct = productRepository.findById("123");

        assertNotNull(foundProduct);
    }

    @Test
    void testFindByIdEmptyProductRepository() {

        Product foundProduct = productRepository.findById("4");

        assertNull(foundProduct);
    }

    @Test
    void testFindByIdNotFound() {
        Product product1 = new Product();
        product1.setProductId("123");
        product1.setProductName("Cah Kangkung");
        product1.setProductQuantity(1);
        productRepository.create(product1);

        Product foundProduct = productRepository.findById("1");

        assertNull(foundProduct);
    }

}
