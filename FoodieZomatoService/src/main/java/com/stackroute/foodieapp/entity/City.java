package com.stackroute.foodieapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {

	@JsonProperty("location_suggestions")
	private List<LocationSuggestions> lSuggestions;
	private String status;

	@JsonProperty("has_more")
	private String hasMore;
	
	@JsonProperty("has_total")
	private String hasTotal;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHasMore() {
		return hasMore;
	}

	public void setHasMore(String hasMore) {
		this.hasMore = hasMore;
	}

	public String getHasTotal() {
		return hasTotal;
	}

	public void setHasTotal(String hasTotal) {
		this.hasTotal = hasTotal;
	}

	public List<LocationSuggestions> getlSuggestions() {
		return lSuggestions;
	}

	public void setlSuggestions(List<LocationSuggestions> lSuggestions) {
		this.lSuggestions = lSuggestions;
	}

}
