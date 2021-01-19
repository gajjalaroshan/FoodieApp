package com.stackroute.foodieapp.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.foodieapp.domain.UserRestaurant;
import com.stackroute.foodieapp.repositories.UserRestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FoodieRestaurantControllerTest {

	@Autowired
	private MockMvc userRestMockMvc;

	@MockBean
	private UserRestaurantRepository userRepo;

	@InjectMocks
	private UserRestaurantController uRController;

	private UserRestaurant userRestaurant;
	private List<UserRestaurant> userRestSet;
	private List<UserRestaurant> userRestSet1;

	private String token = "";

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		userRestaurant = new UserRestaurant();
		userRestSet1 = new ArrayList<UserRestaurant>();
		userRestSet = new ArrayList<UserRestaurant>();
		token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW5kaHlhMkBnbWFpbC5jb20iLCJpYXQiOjE1NTg0NjYxOTF9.ml-XybpiT9X1TMvS6obbwZrVRtaCcNNYx3tJldmjiR4";

		UserRestaurant userRestaurant1 = new UserRestaurant();
		UserRestaurant userRestaurant2 = new UserRestaurant();
		UserRestaurant userRestaurant3 = new UserRestaurant();
		UserRestaurant userRestaurant4 = new UserRestaurant();
		UserRestaurant userRestaurant5 = new UserRestaurant();

		userRestaurant.setEmail("Sangeetha2@gmail.com");
		userRestaurant.setId(13);
		userRestaurant.setRestaurantAddress("Aditya Jayrag, Road 36&37,Opposite Neeru’s Emporia, Jubilee Hills,Hyderabad");
		userRestaurant.setRestaurantCuisines("Ice Cream,Desserts");
		userRestaurant.setRestaurantId("18280307");
		userRestaurant.setRestaurantImage("https://b.zmtcdn.com/data/pictures/4/91784/da7c191473cdc9701aa97a8cbcd51255.jpg");
		userRestaurant.setRestaurantLatitude("17.4386381532");
		userRestaurant.setRestaurantLongitude("78.3991583437");
		userRestaurant.setRestaurantName("Natural Ice Cream");
		userRestaurant.setRestaurantRatings("4.9");

		userRestaurant1.setEmail("Sangeetha2@gmail.com");
		userRestaurant1.setId(13);
		userRestaurant1.setRestaurantAddress("Aditya Jayrag, Road 36&37,Opposite Neeru’s Emporia, Jubilee Hills,Hyderabad");
		userRestaurant1.setRestaurantCuisines("Ice Cream,Desserts");
		userRestaurant1.setRestaurantId("18280307");
		userRestaurant1.setRestaurantImage("https://b.zmtcdn.com/data/pictures/4/91784/da7c191473cdc9701aa97a8cbcd51255.jpg");
		userRestaurant1.setRestaurantLatitude("17.4386381532");
		userRestaurant1.setRestaurantLongitude("78.3991583437");
		userRestaurant1.setRestaurantName("Natural Ice Cream");
		userRestaurant1.setRestaurantRatings("4.9");

		userRestaurant2.setEmail("Sangeetha2@gmail.com");
		userRestaurant2.setId(24);
		userRestaurant2.setRestaurantAddress("Aditya Jayrag, Road 36&37,Opposite Neeru’s Emporia, Jubilee Hills,Hyderabad");
		userRestaurant2.setRestaurantCuisines("Ice Cream,Desserts");
		userRestaurant2.setRestaurantId("123456");
		userRestaurant2.setRestaurantImage("https://b.zmtcdn.com/data/pictures/4/91784/da7c191473cdc9701aa97a8cbcd51255.jpg");
		userRestaurant2.setRestaurantLatitude("17.4386381532");
		userRestaurant2.setRestaurantLongitude("78.3991583437");
		userRestaurant2.setRestaurantName("Natural Ice Cream");
		userRestaurant2.setRestaurantRatings("4.9");

		userRestaurant3.setEmail("Sangeetha4@gmail.com");
		userRestaurant3.setId(25);
		userRestaurant3.setRestaurantAddress("Aditya Jayrag, Road 36&37,Opposite Neeru’s Emporia, Jubilee Hills,Hyderabad");
		userRestaurant3.setRestaurantCuisines("Ice Cream,Desserts");
		userRestaurant3.setRestaurantId("12345678");
		userRestaurant3.setRestaurantImage("https://b.zmtcdn.com/data/pictures/4/91784/da7c191473cdc9701aa97a8cbcd51255.jpg");
		userRestaurant3.setRestaurantLatitude("17.4386381532");
		userRestaurant3.setRestaurantLongitude("78.3991583437");
		userRestaurant3.setRestaurantName("Natural Ice Cream");
		userRestaurant3.setRestaurantRatings("4.9");

		userRestaurant4.setEmail("Sangeetha2@gmail.com");
		userRestaurant4.setId(26);
		userRestaurant4.setRestaurantAddress("Aditya Jayrag, Road 36&37,Opposite Neeru’s Emporia, Jubilee Hills,Hyderabad");
		userRestaurant4.setRestaurantCuisines("Ice Cream,Desserts");
		userRestaurant4.setRestaurantId("257456");
		userRestaurant4.setRestaurantImage("https://b.zmtcdn.com/data/pictures/4/91784/da7c191473cdc9701aa97a8cbcd51255.jpg");
		userRestaurant4.setRestaurantLatitude("17.4386381532");
		userRestaurant4.setRestaurantLongitude("78.3991583437");
		userRestaurant4.setRestaurantName("Natural Ice Cream");
		userRestaurant4.setRestaurantRatings("4.9");

		userRestaurant5.setEmail("Sangeetha2@gmail.com");
		userRestaurant5.setId(26);
		userRestaurant5.setRestaurantAddress("Aditya Jayrag, Road 36&37,Opposite Neeru’s Emporia, Jubilee Hills,Hyderabad");
		userRestaurant5.setRestaurantCuisines("Ice Cream,Desserts");
		userRestaurant5.setRestaurantId("257456");
		userRestaurant5.setRestaurantImage("https://b.zmtcdn.com/data/pictures/4/91784/da7c191473cdc9701aa97a8cbcd51255.jpg");
		userRestaurant5.setRestaurantLatitude("17.4386381532");
		userRestaurant5.setRestaurantLongitude("78.3991583437");
		userRestaurant5.setRestaurantName("Natural Ice Cream");
		userRestaurant5.setRestaurantRatings("4.9");

		userRestSet.addAll(
				Arrays.asList(userRestaurant1, userRestaurant2, userRestaurant3, userRestaurant4, userRestaurant5));
		userRestSet1.addAll(Arrays.asList(userRestaurant1, userRestaurant2));

	}

	private static String asJsonString(final Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}

	@Test
	public void addFavRestaurantsTest() throws Exception {
		Mockito.when(userRepo.save(userRestaurant)).thenReturn(userRestaurant);
		String message = "Success";
		MvcResult result = userRestMockMvc
				.perform(put("/api/restaurantservice/addFavRestaurants").header("authorization", "Bearer " + token)
						.contentType(MediaType.APPLICATION_JSON).content(asJsonString(userRestaurant)))
				.andExpect(status().isAccepted()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);

	}

	@Test
	public void fetchFavRestaurants() throws Exception {
		Mockito.when(userRepo.findAllByEmail(Mockito.anyString())).thenReturn(userRestSet1);
		MvcResult result = userRestMockMvc
				.perform(get("/api/restaurantservice/getFavRestaurants").header("authorization", "Bearer " + token))
				.andExpect(status().isOk()).andReturn();

		String respLength = result.getResponse().getContentAsString();
		assertEquals(asJsonString(userRestSet1), respLength);
		Mockito.verify(userRepo, Mockito.times(1)).findAllByEmail(Mockito.anyString());
	}

	@Test
	public void fetchFavRestaurantsRestaurantsNotFoundExceptionTest() throws Exception {
		Mockito.when(userRepo.findAllByEmail(Mockito.anyString())).thenReturn(null);
		MvcResult result = userRestMockMvc
				.perform(get("/api/restaurantservice/getFavRestaurants").header("authorization", "Bearer " + token))
				.andExpect(status().isConflict()).andReturn();

		int userRestSet1 = result.getResponse().getContentLength();
		assertEquals(0, userRestSet1);
		Mockito.verify(userRepo, Mockito.times(1)).findAllByEmail(Mockito.anyString());
	}

	@Test
	public void deleteFavRestaurantTest() throws Exception {
		Mockito.when(userRepo.findAllByEmailAndRestaurantId(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(userRestSet);
		String message = "Restaurant Removed from Favorites";
		MvcResult result = userRestMockMvc.perform(delete("/api/restaurantservice/deleteFavRestaurant/{id}", "18280307")
				.header("authorization", "Bearer " + token)).andExpect(status().isOk()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).findAllByEmailAndRestaurantId(Mockito.anyString(),
				Mockito.anyString());
	}

	@Test
	public void deleteFavRestaurantDeletedRestaurantTest() throws Exception {
		Mockito.when(userRepo.findAllByEmailAndRestaurantId(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		String message = "Restaurant already deleted!";
		MvcResult result = userRestMockMvc.perform(delete("/api/restaurantservice/deleteFavRestaurant/{id}", "18280307")
				.header("authorization", "Bearer " + token)).andExpect(status().isConflict()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).findAllByEmailAndRestaurantId(Mockito.anyString(),
				Mockito.anyString());
	}

	@Test
	public void deleteAllFavRestaurantTest() throws Exception {
		Mockito.when(userRepo.findAllByEmail(Mockito.anyString())).thenReturn(userRestSet);
		Mockito.doNothing().when(userRepo).deleteByEmail(Mockito.anyString());

		String message = "Cleared All Favorite Restauarants";
		MvcResult result = userRestMockMvc.perform(
				delete("/api/restaurantservice/deleteAllFavRestaurant").header("authorization", "Bearer " + token))
				.andExpect(status().isOk()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).findAllByEmail(Mockito.anyString());
	}

	@Test
	public void deleteAllFavRestaurantNoRestaurantTest() throws Exception {
		Mockito.when(userRepo.findAllByEmail(Mockito.anyString())).thenReturn(null);
		String message = "Restaurants not found!";
		MvcResult result = userRestMockMvc.perform(
				delete("/api/restaurantservice/deleteAllFavRestaurant").header("authorization", "Bearer " + token)
						.contentType(MediaType.APPLICATION_JSON).content(asJsonString(userRestaurant)))
				.andExpect(status().isConflict()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).findAllByEmail(Mockito.anyString());
	}
}
