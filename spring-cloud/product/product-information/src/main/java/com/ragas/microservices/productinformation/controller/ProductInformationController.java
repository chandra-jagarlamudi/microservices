/**
 * 
 */
package com.ragas.microservices.productinformation.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ragas.microservices.productinformation.model.ProductDetails;
import com.ragas.microservices.productinformation.service.ProductInformationService;

/**
 * @author Chandra Jagarlamudi
 *
 */

@RestController
public class ProductInformationController {

	private static Logger log = LoggerFactory.getLogger(ProductInformationController.class);

	@Autowired
	Environment env;

	@Autowired
	ProductInformationService productInformationService;

	@GetMapping
	public String getProductInformationApi() {
		log.info("Product Information API running on port:{}", env.getProperty("local.server.port"));
		return "{\"timestamp\":\"" + new Date() + "\",\"content\":\"Welcome to Product Information API\",\"port\":\""
				+ env.getProperty("local.server.port") + "\"}";
	}

	@GetMapping("/ribbon-resttemplate/products/{productId}")
	public ResponseEntity<ProductDetails> getProductInformationThroughRibbon(@PathVariable Long productId) {
		log.info("Product Information API - Serving product details using Ribbon and Rest Template on port:{}",
				env.getProperty("local.server.port"));
		return productInformationService.getProductInformation(productId);
	}

	@GetMapping("/feign-hystrix/products/{productId}")
	public ResponseEntity<ProductDetails> getProductInformationThroughFeign(@PathVariable Long productId) {
		log.info("Product Information API - Serving product details using Feign and Hystrix on port:{}", productId,
				env.getProperty("local.server.port"));
		return productInformationService.getProductInformation(productId);
	}

}
