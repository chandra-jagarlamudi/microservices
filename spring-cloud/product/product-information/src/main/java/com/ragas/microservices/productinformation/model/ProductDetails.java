/**
 * 
 */
package com.ragas.microservices.productinformation.model;

import java.util.List;

import com.ragas.microservices.core.product.model.Product;
import com.ragas.microservices.core.recommendation.model.Recommendation;
import com.ragas.microservices.core.review.model.Review;

/**
 * @author Chandra Jagarlamudi
 *
 */
public class ProductDetails {
	private Long productId;
    private String name;
    private String description;
    private List<Recommendation> recommendations;
    private List<Review> reviews;
    
    public ProductDetails(Product product, List<Recommendation> recommendations, List<Review> reviews) {
        this.productId = product.getProductId();
        this.name = product.getProductName();
        this.description = product.getProductDescription();
        this.recommendations = recommendations;
        this.reviews = reviews;
    }

	public Long getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Recommendation> getRecommendations() {
		return recommendations;
	}

	public List<Review> getReviews() {
		return reviews;
	}

}
