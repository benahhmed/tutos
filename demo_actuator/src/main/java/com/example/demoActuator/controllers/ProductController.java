package com.example.demoActuator.controllers;

import com.example.demoActuator.dao.ProductRepository;
import com.example.demoActuator.entities.Product;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "productController")
@ELBeanName(value = "productController")
@Join(path = "/product", to = "/product/product-form.jsf")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    private Product product = new Product();

    public String save() {
        productRepository.save(product);
        product = new Product();
        return "/product/product-list.xhtml?faces-redirect=true";
    }

    public Product getProduct() {
        return product;
    }
}
