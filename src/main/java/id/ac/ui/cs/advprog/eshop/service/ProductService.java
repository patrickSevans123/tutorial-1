package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    void delete(String id);
    Product findById(String id);
    void edit(Product editedProduct);
    List<Product> findAll();
}
