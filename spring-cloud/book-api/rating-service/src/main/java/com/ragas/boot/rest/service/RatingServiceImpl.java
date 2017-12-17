/**
 * 
 */
package com.ragas.boot.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ragas.boot.rest.domain.Rating;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Component
public class RatingServiceImpl implements RatingService {
	
	List<Rating> ratingLists = createRatings();

	@Override
	public List<Rating> findAllRatings() {
		return ratingLists;
	}

	@Override
	public List<Rating> findRatingsByBookId(long bookId) {
		return null;
	}

	@Override
	public Rating createRating(Rating rating) {
		return null;
	}

	@Override
	public void deleteRating(long ratingId) {
		
	}

	@Override
	public Rating updateRating(Rating rating, long ratingId) {
		return null;
	}

	@Override
	public Rating updateRating(Map<String, String> updates, long ratingId) {
		return null;
	}
	
	private List<Rating> createRatings(){
		List<Rating> ratingsList = new ArrayList<Rating>();
		return ratingsList;
	}

}
