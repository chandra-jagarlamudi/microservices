/**
 * 
 */
package com.ragas.microservices.core.product.model;

/**
 * @author Chandra Jagarlamudi
 *
 */
public class Product {
	
	long productId;
	String productName;
	String productDescription;
	
	public Product() {
	}

	public Product(long productId, String productName, String productDescription) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
}
