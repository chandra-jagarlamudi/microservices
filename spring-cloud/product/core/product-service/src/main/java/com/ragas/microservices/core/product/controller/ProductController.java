/**
 * 
 */
package com.ragas.microservices.core.product.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ragas.microservices.core.product.model.Product;
import com.ragas.microservices.core.product.service.ProductService;

/**
 * @author Chandra Jagarlamudi
 *
 */

@RestController
public class ProductController {

	private static Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	Environment env;

	@Autowired
	ProductService productService;

	@GetMapping
	public String getApiMessage() {
		log.info("Product API running on port:{}", env.getProperty("local.server.port"));
		return "{\"timestamp\":\"" + new Date() + "\",\"content\":\"Welcome to Product API\",\"port\":\""
				+ env.getProperty("local.server.port") + "\"}";
	}

	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable Long productId) {
		log.info("Serving product information for ProductId:{} on port:{}", productId,
				env.getProperty("local.server.port"));
		return productService.getProduct(productId);
	}
}
