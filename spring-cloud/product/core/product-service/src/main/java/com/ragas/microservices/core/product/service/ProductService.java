/**
 * 
 */
package com.ragas.microservices.core.product.service;

import org.springframework.stereotype.Component;

import com.ragas.microservices.core.product.model.Product;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Component
public class ProductService {
	
	public Product getProduct(Long productId) {
		return new Product(productId, "Product - Name","Product - Description");	
	}
}
