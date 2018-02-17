/**
 * 
 */
package com.ragas.microservices.productinformation.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ragas.microservices.core.product.model.Product;
import com.ragas.microservices.core.recommendation.model.Recommendation;
import com.ragas.microservices.core.review.model.Review;
import com.ragas.microservices.productinformation.model.ProductDetails;
import com.ragas.microservices.productinformation.service.util.ServiceUtil;

/**
 * @author Chandra Jagarlamudi
 *
 */

@Service
public class ProductInformationService {
	private static final Logger LOG = LoggerFactory.getLogger(ProductInformationService.class);

	@Autowired
	ProductInformationIntegration productInformationIntegration;

	@Autowired
	ServiceUtil serviceUtil;

	public ResponseEntity<ProductDetails> getProductInformation(Long productId) {
		// 1. First get mandatory product information
		LOG.debug("Calling Product-Service to get details of productID: {}", productId);
		ResponseEntity<Product> productInformation = productInformationIntegration.getProduct(productId);

		if (!productInformation.getStatusCode().is2xxSuccessful()) {
			// We can't proceed, return whatever fault we got from the getProduct call
			return serviceUtil.createResponse(null, productInformation.getStatusCode());
		}

		// 2. Get optional recommendations
		List<Recommendation> recommendations = new ArrayList<Recommendation>();
		try {
			LOG.debug("Calling Recommendation-Service to get recommendations for productID: {}", productId);
			ResponseEntity<List<Recommendation>> productRecommendations = productInformationIntegration
					.getRecommendations(productId);
			if (!productRecommendations.getStatusCode().is2xxSuccessful()) {
				// Something went wrong with getRecommendations, simply skip the
				// recommendation-information in the response
				LOG.debug("Call to getRecommendations failed: {}", productRecommendations.getStatusCode());
			} else {
				recommendations = productRecommendations.getBody();
			}
		} catch (Exception e) {
			LOG.error("Call to getRecommendations failed: {}", e);
		}

		// 3. Get optional reviews
		List<Review> reviews = new ArrayList<Review>();
		try {
			LOG.debug("Calling Review-Service to get reviews for productID: {}", productId);
			ResponseEntity<List<Review>> productReviews = productInformationIntegration.getReviews(productId);
			if (!productReviews.getStatusCode().is2xxSuccessful()) {
				// Something went wrong with getReviews, simply skip the review-information in
				// the response
				LOG.debug("Call to getReviews failed: {}", productReviews.getStatusCode());
			} else {
				reviews = productReviews.getBody();
			}
		} catch (Exception e) {
			LOG.error("Call to getReviews failed: {}", e);
		}
		return serviceUtil.createOkResponse(new ProductDetails(productInformation.getBody(), recommendations, reviews));
	}

}
