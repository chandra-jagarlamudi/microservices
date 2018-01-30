/**
 * 
 */
package com.ragas.microservice.composite.product.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.ragas.microservice.composite.product.service.util.Util;
import com.ragas.microservice.core.product.model.Product;
import com.ragas.microservice.core.recommendation.model.Recommendation;
import com.ragas.microservice.core.review.model.Review;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Component
public class ProductCompositeIntegration {
	private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeIntegration.class);

	@Autowired
	Util util;

	private RestTemplate restTemplate = new RestTemplate();

	// -------- //
	// PRODUCTS //
	// -------- //
	public ResponseEntity<Product> getProduct(int productId) {

		URI uri = util.getServiceUrl("product", "http://localhost:8081/product");
		String url = uri.toString() + "/product/" + productId;
		LOG.debug("GetProduct from URL: {}", url);

		ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
		LOG.debug("GetProduct http-status: {}", resultStr.getStatusCode());
		LOG.debug("GetProduct body: {}", resultStr.getBody());

		Product product = response2Product(resultStr);
		LOG.debug("GetProduct.id: {}", product.getProductId());

		return util.createOkResponse(product);
	}

	// --------------- //
	// RECOMMENDATIONS //
	// --------------- //
	public ResponseEntity<List<Recommendation>> getRecommendations(int productId) {
		try {
			LOG.info("GetRecommendations...");

			URI uri = util.getServiceUrl("recommendation", "http://localhost:8081/recommendation");

			String url = uri.toString() + "/recommendation?productId=" + productId;
			LOG.debug("GetRecommendations from URL: {}", url);

			ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
			LOG.debug("GetRecommendations http-status: {}", resultStr.getStatusCode());
			LOG.debug("GetRecommendations body: {}", resultStr.getBody());

			List<Recommendation> recommendations = response2Recommendations(resultStr);
			LOG.debug("GetRecommendations.cnt {}", recommendations.size());

			return util.createOkResponse(recommendations);
		} catch (Throwable t) {
			LOG.error("getRecommendations error", t);
			throw t;
		}
	}

	// ------- //
	// REVIEWS //
	// ------- //
	public ResponseEntity<List<Review>> getReviews(int productId) {
		LOG.info("GetReviews...");

		URI uri = util.getServiceUrl("review", "http://localhost:8081/review");

		String url = uri.toString() + "/review?productId=" + productId;
		LOG.debug("GetReviews from URL: {}", url);

		ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
		LOG.debug("GetReviews http-status: {}", resultStr.getStatusCode());
		LOG.debug("GetReviews body: {}", resultStr.getBody());

		List<Review> reviews = response2Reviews(resultStr);
		LOG.debug("GetReviews.cnt {}", reviews.size());

		return util.createOkResponse(reviews);
	}

	// ----- //
	// UTILS //
	// ----- //

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
		return reviewsReader = mapper.reader(new TypeReference<List<Review>>() {});
	}

	public Product response2Product(ResponseEntity<String> response) {
		try {
			return getProductReader().readValue(response.getBody());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private List<Recommendation> response2Recommendations(ResponseEntity<String> response) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List list = mapper.readValue(response.getBody(), new TypeReference<List<Recommendation>>() {});
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
			List list = mapper.readValue(response.getBody(), new TypeReference<List<Review>>() {});
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
