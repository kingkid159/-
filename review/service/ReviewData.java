package review.service;

import review.model.Review;

public class ReviewData {
	private Review review;
	private Review review1;
	public ReviewData(Review review, Review review1) {
		this.review=review;
		this.review1=review1;
		System.out.println("review="+review);
	}
	public Review getReview() {
		return review;
	}
	@Override
	public String toString() {
		return "ReviewData [review=" + review + ", review1=" + review1 + "]";
	}
	public String getContent() {
		return review1.getContent();
	}

}
