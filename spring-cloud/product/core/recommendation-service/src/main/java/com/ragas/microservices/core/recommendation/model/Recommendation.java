/**
 * 
 */
package com.ragas.microservices.core.recommendation.model;

/**
 * @author Chandra Jagarlamudi
 *
 */
public class Recommendation {
	private long productId;
    private long recommendationId;
    private String author;
    private int rate;
    private String content;
    
	public Recommendation() {
	}

	public Recommendation(long productId, long recommendationId, String author, int rate, String content) {
		this.productId = productId;
		this.recommendationId = recommendationId;
		this.author = author;
		this.rate = rate;
		this.content = content;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(long recommendationId) {
		this.recommendationId = recommendationId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
