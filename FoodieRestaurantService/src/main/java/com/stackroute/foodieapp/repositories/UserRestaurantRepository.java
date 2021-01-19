package com.stackroute.foodieapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.foodieapp.domain.UserRestaurant;

public interface UserRestaurantRepository extends JpaRepository<UserRestaurant, String> {

	List<UserRestaurant> findAllByEmail(String email);

	@Modifying
	@Transactional
	@Query(value="delete from UserRestaurant u where u.email = ?1 and u.restaurantId = ?2")
	void deleteByEmailAndRestaurantId(String email, String restaurantId);

	@Modifying
	@Transactional
	@Query(value="delete from UserRestaurant u where u.email = ?1")
	void deleteByEmail(String email);

	@Query(value="select u from UserRestaurant u where u.email = ?1 and u.restaurantId = ?2")
	List<UserRestaurant> findAllByEmailAndRestaurantId(String email, String restaurantId);

}
