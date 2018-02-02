/**
 * 
 */
package com.ragas.microservice.composite.product.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ragas.microservice.composite.product.model.ProductAggregated;
import com.ragas.microservice.composite.product.service.util.Util;
import com.ragas.microservice.core.product.model.Product;
import com.ragas.microservice.core.recommendation.model.Recommendation;
import com.ragas.microservice.core.review.model.Review;

/**
 * @author Chandra Jagarlamud
 *
 */
@RestController
public class ProductCompositeService {
	private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeService.class);

    @Autowired
    ProductCompositeIntegration integration;

    @Autowired
    Util util;

    @GetMapping("/product")
    public String getProduct() {
        return "{\"timestamp\":\"" + new Date() + "\",\"content\":\"Hello from ProductAPi\"}";
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductAggregated> getProduct(@PathVariable int productId) {

        // 1. First get mandatory product information
        ResponseEntity<Product> productResult = integration.getProduct(productId);

        if (!productResult.getStatusCode().is2xxSuccessful()) {
            // We can't proceed, return whatever fault we got from the getProduct call
            return util.createResponse(null, productResult.getStatusCode());
        }

        // 2. Get optional recommendations
        List<Recommendation> recommendations = null;
        try {
            ResponseEntity<List<Recommendation>> recommendationResult = integration.getRecommendations(productId);
            if (!recommendationResult.getStatusCode().is2xxSuccessful()) {
                // Something went wrong with getRecommendations, simply skip the recommendation-information in the response
                LOG.debug("Call to getRecommendations failed: {}", recommendationResult.getStatusCode());
            } else {
                recommendations = recommendationResult.getBody();
            }
        } catch (Throwable t) {
            LOG.error("getProduct erro ", t);
            throw t;
        }


        // 3. Get optional reviews
        ResponseEntity<List<Review>> reviewsResult = integration.getReviews(productId);
        List<Review> reviews = null;
        if (!reviewsResult.getStatusCode().is2xxSuccessful()) {
            // Something went wrong with getReviews, simply skip the review-information in the response
            LOG.debug("Call to getReviews failed: {}", reviewsResult.getStatusCode());
        } else {
            reviews = reviewsResult.getBody();
        }

        return util.createOkResponse(new ProductAggregated(productResult.getBody(), recommendations, reviews));
    }
}
