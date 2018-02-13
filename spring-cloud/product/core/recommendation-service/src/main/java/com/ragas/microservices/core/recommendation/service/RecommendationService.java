/**
 * 
 */
package com.ragas.microservices.core.recommendation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ragas.microservices.core.recommendation.model.Recommendation;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Component
public class RecommendationService {
	
	public List<Recommendation> getRecommendations(Long productId){
		List<Recommendation> list = new ArrayList<>();
        list.add(new Recommendation(productId, 1, "Author 1", 1, "Content 1"));
        list.add(new Recommendation(productId, 2, "Author 2", 2, "Content 2"));
        list.add(new Recommendation(productId, 3, "Author 3", 3, "Content 3"));

        return list;
	}
}
