/**
 * 
 */
package com.ragas.boot.rest.service;

import java.util.List;
import java.util.Map;

import com.ragas.boot.rest.domain.Rating;

/**
 * @author Chandra Jagarlamudi
 *
 */
public interface RatingService {

	List<Rating> findAllRatings();

	List<Rating> findRatingsByBookId(long bookId);

	Rating createRating(Rating rating);

	void deleteRating(long ratingId);

	Rating updateRating(Rating rating, long ratingId);

	Rating updateRating(Map<String, String> updates, long ratingId);

}
