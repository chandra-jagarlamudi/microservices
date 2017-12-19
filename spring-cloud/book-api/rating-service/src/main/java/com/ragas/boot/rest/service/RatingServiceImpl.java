/**
 * 
 */
package com.ragas.boot.rest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.ragas.boot.rest.exception.RatingNotFoundException;
import com.ragas.boot.rest.persistance.dao.RatingRepository;
import com.ragas.boot.rest.persistance.model.Rating;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Component
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public List<Rating> findRatingsByBookId(Long bookId) {
		return ratingRepository.findRatingsByBookId(bookId);
	}

	@Override
	public List<Rating> findAllRatings() {
		return (List<Rating>) ratingRepository.findAll();
	}

	public Rating findRatingById(Long ratingId) {
		return Optional.ofNullable(ratingRepository.findOne(ratingId))
				.orElseThrow(() -> new RatingNotFoundException("Rating not found. ID: " + ratingId));
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Rating createRating(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteRating(Long ratingId) {
		ratingRepository.delete(ratingId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Rating updateRating(Map<String, String> updates, Long ratingId) {
		final Rating rating = findRatingById(ratingId);
		updates.keySet().forEach(key -> {
			switch (key) {
			case "stars":
				rating.setStars(Integer.parseInt(updates.get(key)));
				break;
			}
		});
		return ratingRepository.save(rating);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Rating updateRating(Rating rating, Long ratingId) {
		Preconditions.checkNotNull(rating);
		Preconditions.checkState(rating.getId() == ratingId);
		Preconditions.checkNotNull(ratingRepository.findOne(ratingId));
		return ratingRepository.save(rating);
	}

}
