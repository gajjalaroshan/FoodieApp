package com.stackroute.foodieapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.stackroute.foodieapp.entity.City;
import com.stackroute.foodieapp.entity.Restaurantt;
import com.stackroute.foodieapp.entity.TotalRestaurants;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/zomatoservice")
public class ZomatoController {

	private static final String ZOMATOAPIURL = "https://developers.zomato.com/api/v2.1/";

	@GetMapping("/getRestaurants")
	public List<Restaurantt> getRestaurants(@RequestHeader(value = "user-key") String userkey,
			@RequestParam(name = "cityName", required = true) String cityName,
			@RequestParam(name = "cuisineName", required = false) String cuisineName) {

		List<Restaurantt> restaurants = new ArrayList<Restaurantt>();
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.set("user-key", userkey);
			headers.set("Accept", "application/json");
			headers.set("content-type", "application/json");
			HttpEntity<String> entity = new HttpEntity<>(headers);

			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(ZOMATOAPIURL + "cities").queryParam("q",
					cityName);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<City> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
					entity, City.class);

			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				City city = responseEntity.getBody();
				String cityId = city.getlSuggestions().get(0).getCityId();
				restaurants = getAllRestaurants(userkey, cityId, cuisineName);

			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return restaurants;
	}

	private List<Restaurantt> getAllRestaurants(String userkey, String cityId, String cuisineName) {

		List<Restaurantt> restaurants = new ArrayList<Restaurantt>();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("user-key", userkey);
			HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);

			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(ZOMATOAPIURL + "search")
					.queryParam("entity_id", cityId).queryParam("entity_type", "city").queryParam("q", cuisineName)
					.queryParam("sort", "rating").queryParam("count", "20");

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<TotalRestaurants> responseEntity = restTemplate.exchange(uriBuilder.toUriString(),
					HttpMethod.GET, httpEntity, TotalRestaurants.class);

			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				TotalRestaurants totalRestaurants = responseEntity.getBody();
				restaurants = totalRestaurants.getRestaurants();
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return restaurants;
	}

	/*
	 * @GetMapping("/getReviews") private String getReviews(@RequestParam String
	 * restaurantId, String userKey) { String TotalReviews = "";
	 * 
	 * try { UriComponentsBuilder uriBuilder =
	 * UriComponentsBuilder.fromHttpUrl(ZOMATOAPIURL + "reviews")
	 * .queryParam("res_id", restaurantId);
	 * 
	 * HttpHeaders httpHeaders = new HttpHeaders(); httpHeaders.set("user-key",
	 * userKey);
	 * 
	 * HttpEntity<String> httpEntity = new HttpEntity<String>("parameters",
	 * httpHeaders); RestTemplate restTemplate = new RestTemplate();
	 * 
	 * ResponseEntity<String> responseEntity =
	 * restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
	 * httpEntity, String.class);
	 * 
	 * if (responseEntity.getStatusCode() == HttpStatus.OK) { TotalReviews =
	 * responseEntity.getBody(); }
	 * 
	 * } catch (Exception e) { e.getStackTrace(); }
	 * 
	 * return TotalReviews;
	 * 
	 * }
	 */

}
