package com.stackroute.foodieapp.services;

import java.util.Map;

import com.stackroute.foodieapp.model.User;

public interface IJWTService {
	
	Map<String, String> generateToken(User user);

}
