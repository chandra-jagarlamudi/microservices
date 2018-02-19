/**
 * 
 */
package com.ragas.microservices.productinformation.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.ragas.microservices.core.product.model.Product;
import com.ragas.microservices.core.recommendation.model.Recommendation;
import com.ragas.microservices.core.review.model.Review;
import com.ragas.microservices.productinformation.service.util.ServiceUtil;

/**
 * @author Chandra Jagarlamudi
 *
 */

@Component
public class ProductInformationIntegration {
	private static final Logger LOG = LoggerFactory.getLogger(ProductInformationIntegration.class);

	@Autowired
	ServiceUtil serviceUtil;

	RestTemplate restTemplate = new RestTemplate();

	// PRODUCTS //
	public ResponseEntity<Product> getProduct(Long productId) {
		try {
			LOG.info("Getting Product...");
			URI uri = serviceUtil.getServiceUrl("product-service", "http://localhost:8081/product-service");
			String url = uri.toString() + "/products/" + productId;
			LOG.debug("GetProduct from URL: {}", url);

			ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
			LOG.debug("GetProduct http-status: {}", resultStr.getStatusCode());
			LOG.debug("GetProduct body: {}", resultStr.getBody());

			Product product = response2Product(resultStr);
			LOG.debug("GetProduct.id: {}", product.getProductId());
			
			return serviceUtil.createOkResponse(product);
		}catch (Exception e) {
			LOG.error("Error calling getProduct: {}", e.getMessage());
			return serviceUtil.createOkResponse(new Product());
		}
		
	}

	// RECOMMENDATIONS //
	public ResponseEntity<List<Recommendation>> getRecommendations(Long productId) {
		try {
			LOG.info("Getting Recommendations...");
			URI uri = serviceUtil.getServiceUrl("recommendation-service",
					"http://localhost:8081/recommendation-service");
			String url = uri.toString() + "/recommendations?productId=" + productId;
			LOG.debug("GetRecommendations from URL: {}", url);

			ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
			LOG.debug("GetRecommendations http-status: {}", resultStr.getStatusCode());
			LOG.debug("GetRecommendations body: {}", resultStr.getBody());

			List<Recommendation> recommendations = response2Recommendations(resultStr);
			LOG.debug("GetRecommendations.cnt {}", recommendations.size());

			return serviceUtil.createOkResponse(recommendations);
		} catch (Throwable e) {
			LOG.error("Error calling getRecommendations: {}", e.getMessage());
			return serviceUtil.createResponse(new ArrayList<Recommendation>(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	// REVIEWS //
	public ResponseEntity<List<Review>> getReviews(Long productId) {
		try {
			LOG.info("Getting Reviews...");

			URI uri = serviceUtil.getServiceUrl("review-service", "http://localhost:8081/review-service");

			String url = uri.toString() + "/reviews?productId=" + productId;
			LOG.debug("GetReviews from URL: {}", url);

			ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
			LOG.debug("GetReviews http-status: {}", resultStr.getStatusCode());
			LOG.debug("GetReviews body: {}", resultStr.getBody());

			List<Review> reviews = response2Reviews(resultStr);
			LOG.debug("GetReviews.cnt {}", reviews.size());

			return serviceUtil.createOkResponse(reviews);
		} catch (Throwable e) {
			LOG.error("Error calling getReviews: {}", e.getMessage());
			return serviceUtil.createResponse(new ArrayList<Review>(), HttpStatus.SERVICE_UNAVAILABLE);
		}

	}

	// UTILS //
	private ObjectReader productReader = null;

	private ObjectReader getProductReader() {

		if (productReader != null)
			return productReader;

		ObjectMapper mapper = new ObjectMapper();
		return productReader = mapper.reader(Product.class);
	}

	private ObjectReader reviewsReader = null;

	private ObjectReader getReviewsReader() {
		if (reviewsReader != null)
			return reviewsReader;

		ObjectMapper mapper = new ObjectMapper();
		return reviewsReader = mapper.reader(new TypeReference<List<Review>>() {
		});
	}

	public Product response2Product(ResponseEntity<String> response) {
		try {
			return getProductReader().readValue(response.getBody());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// TODO: Gereralize with <T> method, skip objectReader objects!
	private List<Recommendation> response2Recommendations(ResponseEntity<String> response) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List list = mapper.readValue(response.getBody(), new TypeReference<List<Recommendation>>() {
			});
			List<Recommendation> recommendations = list;
			return recommendations;

		} catch (IOException e) {
			LOG.warn("IO-err. Failed to read JSON", e);
			throw new RuntimeException(e);

		} catch (RuntimeException re) {
			LOG.warn("RTE-err. Failed to read JSON", re);
			throw re;
		}
	}

	private List<Review> response2Reviews(ResponseEntity<String> response) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List list = mapper.readValue(response.getBody(), new TypeReference<List<Review>>() {
			});
			List<Review> reviews = list;
			return reviews;

		} catch (IOException e) {
			LOG.warn("IO-err. Failed to read JSON", e);
			throw new RuntimeException(e);

		} catch (RuntimeException re) {
			LOG.warn("RTE-err. Failed to read JSON", re);
			throw re;
		}
	}
}
