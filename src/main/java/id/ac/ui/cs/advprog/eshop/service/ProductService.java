package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public void delete(String id);
    public Product findById(String id);
    public void edit(Product editedProduct);
    public List<Product> findAll();
}
