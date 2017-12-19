/**
 * 
 */
package com.ragas.boot.rest.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ragas.boot.rest.persistance.model.Rating;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Service
public interface RatingService {

	List<Rating> findAllRatings();

	List<Rating> findRatingsByBookId(Long bookId);

	Rating createRating(Rating rating);

	void deleteRating(Long ratingId);

	Rating updateRating(Rating rating, Long ratingId);

	Rating updateRating(Map<String, String> updates, Long ratingId);

}
