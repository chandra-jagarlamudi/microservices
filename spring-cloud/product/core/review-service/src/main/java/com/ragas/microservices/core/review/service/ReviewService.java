/**
 * 
 */
package com.ragas.microservices.core.review.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ragas.microservices.core.review.model.Review;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Component
public class ReviewService {
	public List<Review> getReviews(Long productId){
		List<Review> list = new ArrayList<>();
        list.add(new Review(productId, 1, "Author 1", "Subject 1", "Content 1"));
        list.add(new Review(productId, 2, "Author 2", "Subject 2", "Content 2"));
        list.add(new Review(productId, 3, "Author 3", "Subject 3", "Content 3"));
        return list;
	}
}
