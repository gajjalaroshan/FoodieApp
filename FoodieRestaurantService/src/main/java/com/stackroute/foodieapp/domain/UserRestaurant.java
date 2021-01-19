package com.stackroute.foodieapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserRestaurants")
public class UserRestaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "email")
	private String email;

	@Column(name = "restaurantId")
	private String restaurantId;

	@Column(name = "restaurantImage")
	private String restaurantImage;

	@Column(name = "restaurantCuisines")
	private String restaurantCuisines;

	@Column(name = "restaurantAddress")
	private String restaurantAddress;

	@Column(name = "restaurantName")
	private String restaurantName;

	@Column(name = "restaurantRatings")
	private String restaurantRatings;

	@Column(name = "restaurantLatitude")
	private String restaurantLatitude;

	@Column(name = "restaurantLongitude")
	private String restaurantLongitude;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantImage() {
		return restaurantImage;
	}

	public void setRestaurantImage(String restaurantImage) {
		this.restaurantImage = restaurantImage;
	}

	public String getRestaurantCuisines() {
		return restaurantCuisines;
	}

	public void setRestaurantCuisines(String restaurantCuisines) {
		this.restaurantCuisines = restaurantCuisines;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantRatings() {
		return restaurantRatings;
	}

	public void setRestaurantRatings(String restaurantRatings) {
		this.restaurantRatings = restaurantRatings;
	}

	public String getRestaurantLatitude() {
		return restaurantLatitude;
	}

	public void setRestaurantLatitude(String restaurantLatitude) {
		this.restaurantLatitude = restaurantLatitude;
	}

	public String getRestaurantLongitude() {
		return restaurantLongitude;
	}

	public void setRestaurantLongitude(String restaurantLongitude) {
		this.restaurantLongitude = restaurantLongitude;
	}

	

}
