/**
 * 
 */
package com.ragas.microservice.core.product.model;

/**
 * @author Chandra Jagarlamudi
 *
 */
public class Product {
	private int productId;
	
	private String name;
	
	private int weight;

	public Product() {
	}

	public Product(int productId, String string, int i) {
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
