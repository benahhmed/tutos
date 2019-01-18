package com.example.demoActuator.controllers;

import com.example.demoActuator.dao.ProductRepository;
import com.example.demoActuator.entities.Product;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope(value = "session")
@Component(value = "listProducts")
@ELBeanName(value = "listProducts")
@Join(path = "/", to = "/product/product-list.jsf")
public class ListProductsController {
	@Autowired
	private ProductRepository productRepository;

	private List<Product> products;

	@Deferred
	@RequestAction
	@IgnorePostback
	public void loadData() {
		products = productRepository.findAll();
	}

	public List<Product> getProducts() {
		return products;
	}

	public String delete(Product product) {
		productRepository.deleteById(product.getId());
		loadData();
		return null;
	}
}
