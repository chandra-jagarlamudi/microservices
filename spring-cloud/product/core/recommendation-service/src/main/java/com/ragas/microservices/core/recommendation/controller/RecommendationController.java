/**
 * 
 */
package com.ragas.microservices.core.recommendation.controller;

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

import com.ragas.microservices.core.recommendation.model.Recommendation;
import com.ragas.microservices.core.recommendation.service.RecommendationService;

/**
 * @author Chandra Jagarlamudi
 *
 */

@RestController
public class RecommendationController {

	private static Logger log = LoggerFactory.getLogger(RecommendationController.class);

	@Autowired
	Environment env;

	@Autowired
	private RecommendationService recommendationService;

	@GetMapping
	public String getApiMessage() {
		log.info("Recommendation API running on port:{}", env.getProperty("local.server.port"));
		return "{\"timestamp\":\"" + new Date() + "\",\"content\":\"Welcome to Recommendation API\",\"port\":\""
				+ env.getProperty("local.server.port") + "\"}";
	}

	@GetMapping("/recommendations")
	public ResponseEntity<List<Recommendation>> getRecommendations(
			@RequestParam(value = "productId", required = true) long productId) {
		log.info("Serving recommendations for ProductId:{} on port:{}", productId,
				env.getProperty("local.server.port"));
		return new ResponseEntity<List<Recommendation>>(recommendationService.getRecommendations(productId),
				HttpStatus.OK);
	}

}
