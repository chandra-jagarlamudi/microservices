/**
 * 
 */
package com.ragas.boot.rest.persistance.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rating implements Serializable {

	private static final long serialVersionUID = -6231233680259267136L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long bookId;
	private int stars;

	public Rating() {
	}

	public Rating(Long bookId, int stars) {
		this.bookId = bookId;
		this.stars = stars;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the bookId
	 */
	public Long getBookId() {
		return bookId;
	}

	/**
	 * @param bookId
	 *            the bookId to set
	 */
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the stars
	 */
	public int getStars() {
		return stars;
	}

	/**
	 * @param stars
	 *            the stars to set
	 */
	public void setStars(int stars) {
		this.stars = stars;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + stars;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stars != other.stars)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", bookId=" + bookId + ", stars=" + stars + "]";
	}
}
