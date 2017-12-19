/**
 * 
 */
package com.ragas.boot.rest.persistance.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ragas.boot.rest.persistance.model.Rating;

/**
 * @author Chandra Jagarlamudi
 *
 */
public interface RatingRepository extends CrudRepository<Rating, Long> {
	List<Rating> findRatingsByBookId(Long bookId);
}
