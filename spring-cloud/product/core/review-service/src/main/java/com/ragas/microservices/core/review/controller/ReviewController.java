/**
 * 
 */
package com.ragas.microservices.core.review.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ragas.microservices.core.review.model.Review;
import com.ragas.microservices.core.review.service.ReviewService;

/**
 * @author Chandra Jagarlamudi
 *
 */

@RestController
public class ReviewController {

	private static Logger log = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	Environment env;

	@Autowired
	private ReviewService reviewService;

	@GetMapping
	public String getApiMessage() {
		log.info("Review API running on port:{}", env.getProperty("local.server.port"));
		return "{\"timestamp\":\"" + new Date() + "\",\"content\":\"Welcome to Review API\",\"port\":\""
				+ env.getProperty("local.server.port") + "\"}";
	}

	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getReviews(@RequestParam(value = "productId", required = true) long productId) {
		log.info("Serving reviews for ProductId:{} on port:{}", productId, env.getProperty("local.server.port"));
		return new ResponseEntity<List<Review>>(reviewService.getReviews(productId), HttpStatus.OK);
	}

}
