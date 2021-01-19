package com.stackroute.foodieapp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRating {
	@JsonProperty("aggregate_rating")
	private String aggrRating;
	@JsonProperty("rating_text")
	private String ratingText;

	public String getRatingText() {
		return ratingText;
	}

	public void setRatingText(String ratingText) {
		this.ratingText = ratingText;
	}

	public String getAggrRating() {
		return aggrRating;
	}

	public void setAggrRating(String aggrRating) {
		this.aggrRating = aggrRating;
	}

}
