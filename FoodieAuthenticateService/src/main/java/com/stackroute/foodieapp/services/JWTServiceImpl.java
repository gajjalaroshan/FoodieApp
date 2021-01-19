package com.stackroute.foodieapp.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.foodieapp.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTServiceImpl implements IJWTService {

	public Map<String, String> generateToken(User user) {
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

		

		return jwtMap;

	}

}
