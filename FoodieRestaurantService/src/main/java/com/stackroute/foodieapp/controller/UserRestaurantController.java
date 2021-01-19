package com.stackroute.foodieapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.foodieapp.domain.UserRestaurant;
import com.stackroute.foodieapp.exceptions.OldRestaurantException;
import com.stackroute.foodieapp.services.IuserRestaurantService;

import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin("*")
@EnableWebMvc
@RequestMapping("/api/restaurantservice")
public class UserRestaurantController {

	@Autowired
	private IuserRestaurantService userRestServ;

	@PutMapping("/addFavRestaurants")
	public ResponseEntity<String> addFavRestaurants(@RequestBody UserRestaurant favRestaurant, HttpServletRequest req) {
		String email = getEmailByRequest(req);
		HttpStatus status;
		String message;
		try {
			favRestaurant.setEmail(email);
			userRestServ.addFavRestaurant(favRestaurant);
			message = "Success";
			status = HttpStatus.ACCEPTED;

		} catch (OldRestaurantException e) {
			message = e.getMessage();
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			message = e.getMessage();
			status = HttpStatus.CONFLICT;
		}

		return new ResponseEntity<String>(message, status);

	}

	@GetMapping("/getFavRestaurants")
	public ResponseEntity<List<UserRestaurant>> fetchFavRestaurants(HttpServletRequest req) {
		HttpStatus status = HttpStatus.CONFLICT;
		List<UserRestaurant> urList = null;
		String email = getEmailByRequest(req);
		try {
			urList = userRestServ.getFavRestaurants(email);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.getMessage();
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<List<UserRestaurant>>(urList, status);
	}

	@DeleteMapping("/deleteFavRestaurant/{id}")
	public ResponseEntity<?> deleteFavRestaurant(@PathVariable("id") String restId, HttpServletRequest req) {
		HttpStatus status;
		String message;
		String email = getEmailByRequest(req);
		try {
			userRestServ.deleteRestaurantById(email, restId);
			message = "Restaurant Removed from Favorites";
			status = HttpStatus.OK;

		} catch (Exception e) {
			message = e.getMessage();
			status = HttpStatus.CONFLICT;
		}

		return new ResponseEntity<String>(message, status);
	}

	@DeleteMapping("/deleteAllFavRestaurant")
	public ResponseEntity<?> deleteAllFavRestaurant(HttpServletRequest req) {
		HttpStatus status;
		String message;
		String email = getEmailByRequest(req);
		try {
			userRestServ.deleteAllFavRestaurant(email);
			message = "Cleared All Favorite Restauarants";
			status = HttpStatus.OK;

		} catch (Exception e) {
			message = e.getMessage();
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<String>(message, status);
	}

	private String getEmailByRequest(HttpServletRequest request) {
		String authHeader = request.getHeader("authorization");
		String token = authHeader.substring(7);
		return Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
	}
}