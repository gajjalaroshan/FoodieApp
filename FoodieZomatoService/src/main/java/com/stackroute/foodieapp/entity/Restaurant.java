package com.stackroute.foodieapp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Restaurant {

	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("featured_image")
	private String featuredImg;
	@JsonProperty("cuisines")
	private String cuisines;
	@JsonProperty("average_cost_for_two")
	private String avgCostForTwo;
	@JsonProperty("photos_url")
	private String photosUrl;
	@JsonProperty("menu_url")
	private String menuUrl;
	@JsonProperty("events_url")
	private String eventsUrl;

	private Location location;

	@JsonProperty("user_rating")
	private UserRating userRating;

	public String getFeaturedImg() {
		return featuredImg;
	}

	public void setFeaturedImg(String featuredImg) {
		this.featuredImg = featuredImg;
	}

	public String getCuisines() {
		return cuisines;
	}

	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}

	public String getAvgCostForTwo() {
		return avgCostForTwo;
	}

	public void setAvgCostForTwo(String avgCostForTwo) {
		this.avgCostForTwo = avgCostForTwo;
	}

	public String getPhotosUrl() {
		return photosUrl;
	}

	public void setPhotosUrl(String photosUrl) {
		this.photosUrl = photosUrl;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getEventsUrl() {
		return eventsUrl;
	}

	public void setEventsUrl(String eventsUrl) {
		this.eventsUrl = eventsUrl;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public UserRating getUserRating() {
		return userRating;
	}

	public void setUserRating(UserRating userRating) {
		this.userRating = userRating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
