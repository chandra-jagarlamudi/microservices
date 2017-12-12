/**
 * 
 */
package com.ragas.boot.domain;

/**
 * @author Chandra Jagarlamudi
 *
 */
public class Rating {
	private long id;
	private long bookId;
	private int stars;
	private String review;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the bookId
	 */
	public long getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the stars
	 */
	public int getStars() {
		return stars;
	}
	/**
	 * @param stars the stars to set
	 */
	public void setStars(int stars) {
		this.stars = stars;
	}
	/**
	 * @return the review
	 */
	public String getReview() {
		return review;
	}
	/**
	 * @param review the review to set
	 */
	public void setReview(String review) {
		this.review = review;
	}
	
	
}
