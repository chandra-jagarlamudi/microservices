/**
 * 
 */
package com.ragas.boot.service;

import java.util.List;
import java.util.Map;

import com.ragas.boot.domain.Rating;


/**
 * @author Chandra Jagarlamudi
 *
 */
public interface RatingService {

	List<Rating> findAllRatings();

	List<Rating> findRatingsByBookId(long bookId);

	Rating createRating(Rating rating);
	
	Rating updateRating(Rating rating, long ratingId);

	Rating updateRating(Map<String, String> updates, long ratingId);

	void deleteRating(long ratingId);

}
