package com.stackroute.foodieapp.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
import com.stackroute.foodieapp.model.User;
import com.stackroute.foodieapp.repositories.UserRepository;
import com.stackroute.foodieapp.services.IJWTService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserRepository userRepo;

	@MockBean
	private IJWTService jwtService;
	private User user;

	private String email;

	@InjectMocks
	private UserController userController;

	@Before
	public void setUp() {
		byte[] array = new byte[8];
		new Random().nextBytes(array);
		email = new String(array, Charset.forName("UTF-8"));
		email.concat("@g.com");
		MockitoAnnotations.initMocks(this);
		user = new User(email, "firstname", "lastname", "pass", "1234567890", "stack", "city");
	}

	@Test
	public void testRegisterUser() throws JsonProcessingException, Exception {

		String message = "User registration successful";
		Mockito.when(userRepo.findUserByEmail(email)).thenReturn(null);
		Mockito.when(userRepo.save(user)).thenReturn(user);
		MvcResult result = mockMvc
				.perform(post("/api/userService/signup").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(jsonToString(user)))
				.andExpect(status().isCreated()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).save(Mockito.any(User.class));
	}

	@Test
	public void testRegisterUserOldUser() throws JsonProcessingException, Exception {

		String message = "Email Id Already Exists";
		Mockito.when(userRepo.findUserByEmail(email)).thenReturn(user);
		MvcResult result = mockMvc
				.perform(post("/api/userService/signup").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(jsonToString(user)))
				.andExpect(status().isAccepted()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).findUserByEmail(email);
	}

	@Test
	public void testLoginUser() throws JsonProcessingException, Exception {
		String message = "logged in successfully";
		String password = user.getPassword();
		Mockito.when(userRepo.findUserByEmailAndPassword(email, password)).thenReturn(user);
		MvcResult result = mockMvc.perform(
				post("/api/userService/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
				.andExpect(status().isOk()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).findUserByEmailAndPassword(user.getEmail(), user.getPassword());
	}

	@Test
	public void testCheckUser() throws JsonProcessingException, Exception {
		String message = "Yes";
		Mockito.when(userRepo.findUserByEmail(email)).thenReturn(user);
		MvcResult result = mockMvc.perform(get("/api/userService/checkUser").param("email", email))
				.andExpect(status().isOk()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).findUserByEmail(email);
	}

	@Test
	public void testCheckUserNo() throws JsonProcessingException, Exception {
		String message = "No";
		Mockito.when(userRepo.findUserByEmail(email)).thenReturn(null);
		MvcResult result = mockMvc.perform(get("/api/userService/checkUser").param("email", email))
				.andExpect(status().isOk()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).findUserByEmail(email);
	}

	@Test
	public void testGetUserInfo() throws JsonProcessingException, Exception {
		String message = "User successfully logged in";
		String jwtoken = "";
		jwtoken = Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		Map<String, String> jwtMap = new HashMap<String, String>();
		jwtMap.put("token", jwtoken);
		jwtMap.put("message", "User successfully logged in");

		jwtMap.put("firstName", user.getFirstName());
		jwtMap.put("lastName", user.getLastName());
		jwtMap.put("city", user.getCity());
		jwtMap.put("state", user.getState());
		jwtMap.put("mobile", user.getMobile());
		jwtMap.put("email", user.getEmail());

		ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>(jwtMap);
		Mockito.when(jwtService.generateToken(user)).thenReturn(concurrentMap);
		Mockito.when(userRepo.findUserByEmail(email)).thenReturn(user);
		MvcResult result = mockMvc.perform(get("/api/userService/getUserInfo").param("email", email))
				.andExpect(status().isOk()).andExpect(jsonPath("$.email").value(email))
				.andExpect(jsonPath("$.message").value(message)).andReturn();
		Mockito.verify(userRepo, Mockito.times(1)).findUserByEmail(email);
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void testGetUserInfoNull() throws JsonProcessingException, Exception {
		Mockito.when(userRepo.findUserByEmail(email)).thenReturn(null);
		MvcResult result = mockMvc.perform(get("/api/userService/getUserInfo").param("email", email))
				.andExpect(status().isOk()).andReturn();
		int obtMessage = result.getResponse().getContentLength();
		assertEquals(obtMessage, 0);
		Mockito.verify(userRepo, Mockito.times(1)).findUserByEmail(email);
	}

	@Test
	public void resetPasswordPassTest() throws Exception {
		String password = user.getPassword();
		String message = "Password reset successful";
		Mockito.when(userRepo.findUserByEmail(email)).thenReturn(user);
		MvcResult result = mockMvc
				.perform(get("/api/userService/resetPassword").param("email", email).param("newPassword", password))
				.andExpect(status().isOk()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).findUserByEmail(email);
	}

	@Test
	public void resetPasswordFailNoUserTest() throws Exception {
		String message = "User not Found";
		String password = user.getPassword();
		Mockito.when(userRepo.findUserByEmail(email)).thenReturn(null);
		MvcResult result = mockMvc
				.perform(get("/api/userService/resetPassword").param("email", email).param("newPassword", password))
				.andExpect(status().isOk()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).findUserByEmail(email);
	}

	@Test
	public void updateUserDetailsPassTest() throws JsonProcessingException, Exception {
		String message = "User Details updated";
		Mockito.when(userRepo.findUserByEmail(email)).thenReturn(user);
		Mockito.when(userRepo.save(user)).thenReturn(user);
		MvcResult result = mockMvc
				.perform(put("/api/userService/updateUserDetails").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
				.andExpect(status().isOk()).andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).findUserByEmail(email);
	}

	@Test
	public void updateUserDetailsFailOldUserTest() throws JsonProcessingException, Exception {
		String message = "Email Id does not Exist";
		Mockito.when(userRepo.findUserByEmail(email)).thenReturn(null);
		MvcResult result = mockMvc.perform(put("/api/userService/updateUserDetails")
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isOk())
				.andReturn();
		String obtMessage = result.getResponse().getContentAsString();
		assertEquals(message, obtMessage);
		Mockito.verify(userRepo, Mockito.times(1)).findUserByEmail(email);
	}

	private static String jsonToString(final Object obj) throws JsonProcessingException {
		String result;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}

}
