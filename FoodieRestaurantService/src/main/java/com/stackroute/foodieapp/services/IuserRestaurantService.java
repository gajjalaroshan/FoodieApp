package com.stackroute.foodieapp.services;

import java.util.List;

import com.stackroute.foodieapp.domain.UserRestaurant;
import com.stackroute.foodieapp.exceptions.OldRestaurantException;
import com.stackroute.foodieapp.exceptions.RestaurantsNotFoundException;

public interface IuserRestaurantService {

	List<UserRestaurant> getFavRestaurants(String email) throws RestaurantsNotFoundException;;

	boolean addFavRestaurant(UserRestaurant restaurant) throws OldRestaurantException;

	boolean deleteRestaurantById(String email, String restaurantId) throws RestaurantsNotFoundException;

	boolean deleteAllFavRestaurant(String email) throws RestaurantsNotFoundException;

}
