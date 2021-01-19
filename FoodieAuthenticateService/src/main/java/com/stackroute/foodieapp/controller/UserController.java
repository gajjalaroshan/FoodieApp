package com.stackroute.foodieapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.foodieapp.exceptions.OldUserException;
import com.stackroute.foodieapp.exceptions.UserNotFoundException;
import com.stackroute.foodieapp.model.User;
import com.stackroute.foodieapp.services.IJWTService;
import com.stackroute.foodieapp.services.IUserService;

@Controller
@EnableWebMvc
@CrossOrigin("*")
@RequestMapping("api/userService")
public class UserController {
	@Autowired
	private IUserService userService;

	@Autowired
	private IJWTService jwtService;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody User userDetails) {
		String message = "User registration Failed";
		HttpStatus status;
		userDetails.setLastName("");
		userDetails.setCity("");
		userDetails.setMobile("");
		userDetails.setState("");
		try {
			userService.saveUser(userDetails);
			message = "User registration successful";
			status = HttpStatus.CREATED;
		} catch (OldUserException e) {
			message = e.getMessage();
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			message = e.getMessage();
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<String>(message, status);

	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginDetails) {
		String message = "Login Failed";
		HttpStatus status = HttpStatus.CONFLICT;
		try {
			String email = loginDetails.getEmail();
			String pwd = loginDetails.getPassword();

			User verifiedUser = new User();

			if (null != email && null != pwd) {
				verifiedUser = userService.findUserByEmailAndPassword(email, pwd);
				if (null != verifiedUser) {
					message = "logged in successfully";
				}
			} else {
				message = "Invalid Email or Password";
			}
			status = HttpStatus.OK;
		} catch (UserNotFoundException e) {
			message = e.getMessage();
			status = HttpStatus.OK;
		} catch (Exception e) {
			message = e.getMessage();
		}
		return new ResponseEntity<String>(message, status);
	}

	@GetMapping("/checkUser")
	public ResponseEntity<String> isUserExists(@RequestParam String email) {
		String isUserExists = "";
		HttpStatus status;
		try {
			isUserExists = userService.isUserExists(email);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.CONFLICT;
		}

		return new ResponseEntity<String>(isUserExists, status);
	}

	@GetMapping("/getUserInfo")
	public ResponseEntity<Map<String, String>> getUserInfo(@RequestParam String email) {
		HttpStatus status = HttpStatus.CONFLICT;
		Map<String, String> jwtMap = null;
		User verifiedUser = new User();
		try {
			verifiedUser = userService.findUserByEmail(email);
			status = HttpStatus.OK;
		}catch (UserNotFoundException e) {
			e.getMessage();
			status = HttpStatus.OK;
		}catch (Exception e) {
			e.getMessage();
		}
		jwtMap = jwtService.generateToken(verifiedUser);
		return new ResponseEntity<Map<String, String>>(jwtMap, status);

	}

	@GetMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestParam String newPassword, @RequestParam String email) {
		String message = "password reset failed";
		HttpStatus status;
		try {
			message = userService.resetPassword(newPassword, email);
			status = HttpStatus.OK;
		} catch (UserNotFoundException e) {
			message = e.getMessage();
			status = HttpStatus.OK;
		} catch (Exception e) {
			message = e.getMessage();
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<String>(message, status);

	}

	@PutMapping("/updateUserDetails")
	public ResponseEntity<?> updateUserDetails(@RequestBody User userDetails) {
		String message = "User Details cannot be updated";
		HttpStatus status;
		try {
			userService.updateUser(userDetails);
			message = "User Details updated";
			status = HttpStatus.OK;
		} catch (OldUserException e) {
			status = HttpStatus.OK;
			message = e.getMessage();
		} catch (Exception e) {
			status = HttpStatus.CONFLICT;
			message = e.getMessage();
		}
		return new ResponseEntity<String>(message, status);

	}

}
