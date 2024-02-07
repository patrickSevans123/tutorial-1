package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    static int id = 0;

    public Product create(Product product) {
        product.setProductId(String.valueOf(id++));
        productData.add(product);
        return product;
    }

    // public void delete(String id) {
    //     for (Product product : productData) {
    //         if (product.getProductId().equals(id)) {
    //             productData.remove(product.getProductId());
    //             return;
    //         }
    //     }
    // }

    public void delete(String id) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(id)) {
                productData.remove(i);
                return;
            }
        }
    }


    public Product findById(String id) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void edit(Product editedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(editedProduct.getProductId())) {
                productData.set(i, editedProduct);
                return;
            }
        }
    }

    

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
