/*package com.stackroute.foodieapp.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FoodieZomatoControllerTests {

	@Autowired
	private MockMvc zomatoMockMVC;

	@InjectMocks
	private ZomatoController zomatoController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testGetRestaurants() throws Exception {

		MvcResult result = zomatoMockMVC
				.perform(get("/api/zomatoservice/getRestaurants").header("user-key", "c9125894b0e68ddc365ced057530fb12")
						.param("cityName", "Chennai").param("cuisineName", "italian"))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	}
}
*/