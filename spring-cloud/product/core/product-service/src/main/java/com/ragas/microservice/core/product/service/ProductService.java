/**
 * 
 */
package com.ragas.microservice.core.product.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ragas.microservice.core.product.model.Product;

/**
 * @author Chandra Jagarlamudi
 *
 */
@RestController
@RequestMapping("/product")
public class ProductService {
	@GetMapping("/{productId}")
    public Product getProduct(@PathVariable int productId) {
        return new Product(productId, "name", 123);
    }
}
