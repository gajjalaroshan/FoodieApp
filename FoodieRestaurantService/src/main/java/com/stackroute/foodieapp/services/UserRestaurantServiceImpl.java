package com.stackroute.foodieapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.foodieapp.domain.UserRestaurant;
import com.stackroute.foodieapp.exceptions.OldRestaurantException;
import com.stackroute.foodieapp.exceptions.RestaurantsNotFoundException;
import com.stackroute.foodieapp.repositories.UserRestaurantRepository;

@Service
public class UserRestaurantServiceImpl implements IuserRestaurantService {

	@Autowired
	private UserRestaurantRepository userRepo;

	public boolean addFavRestaurant(UserRestaurant restaurant) throws OldRestaurantException {
		List<UserRestaurant> userRestaurants;
		userRestaurants = userRepo.findAllByEmailAndRestaurantId(restaurant.getEmail(), restaurant.getRestaurantId());
		if (!userRestaurants.isEmpty()) {
			throw new OldRestaurantException("Restaurant Already Exists!");
		}
		userRepo.save(restaurant);
		return true;
	}

	public List<UserRestaurant> getFavRestaurants(String email) throws RestaurantsNotFoundException {
		List<UserRestaurant> userRestaurants;
		userRestaurants = userRepo.findAllByEmail(email);
		if (userRestaurants == null || userRestaurants.isEmpty()) {
			throw new RestaurantsNotFoundException("Restaurants not found!");
		}
		return userRestaurants;
	}

	public boolean deleteRestaurantById(String email, String restaurantId) throws RestaurantsNotFoundException {
		List<UserRestaurant> userRestaurants;
		userRestaurants = userRepo.findAllByEmailAndRestaurantId(email, restaurantId);
		if (userRestaurants == null) {
			throw new RestaurantsNotFoundException("Restaurant already deleted!");
		}
		userRepo.deleteByEmailAndRestaurantId(email, restaurantId);
		return true;
	}

	public boolean deleteAllFavRestaurant(String email) throws RestaurantsNotFoundException {
		List<UserRestaurant> userRestaurants;
		userRestaurants = userRepo.findAllByEmail(email);
		if (userRestaurants == null || userRestaurants.isEmpty()) {
			throw new RestaurantsNotFoundException("Restaurants not found!");
		}
		userRepo.deleteByEmail(email);

		return true;
	}

}
