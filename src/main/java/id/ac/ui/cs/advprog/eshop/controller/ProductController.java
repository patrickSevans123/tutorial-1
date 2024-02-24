package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String homePage() {
        return "HomePage";
    }

    @GetMapping("/product/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable String id, Model model) {
        service.delete(id);
        return "redirect:../list";
    }

    @GetMapping("/product/edit/{id}")
    public String editProductPage(@PathVariable("id") String id, Model model) {
        Product product = service.findById(id);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/product/edit/{id}")
    public String editProductPost(@PathVariable("id") String id, @ModelAttribute Product product, Model model) {
        product.setProductId(id);
        service.edit(product);
        return "redirect:../list";
    }

    @GetMapping("/product/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }
}