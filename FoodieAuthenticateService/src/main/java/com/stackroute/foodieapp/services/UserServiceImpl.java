package com.stackroute.foodieapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.foodieapp.exceptions.OldUserException;
import com.stackroute.foodieapp.exceptions.UserNotFoundException;
import com.stackroute.foodieapp.model.User;
import com.stackroute.foodieapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepo;

	public boolean saveUser(User user) throws OldUserException, UserNotFoundException {
		User newUser;
		newUser = userRepo.findUserByEmail(user.getEmail());
		if (null != newUser) {
			throw new OldUserException("Email Id Already Exists");
		}
		userRepo.save(user);
		return true;
	}

	public boolean updateUser(User user) throws OldUserException, UserNotFoundException {
		User newUser;

		newUser = userRepo.findUserByEmail(user.getEmail());
		if (null == newUser) {
			throw new OldUserException("Email Id does not Exist");
		}
		user.setPassword(newUser.getPassword());
		user.setEmail(user.getEmail());
		userRepo.save(user);
		return true;
	}

	public String isUserExists(String email) {
		String isUserExists = "Yes";
		User trueUser = userRepo.findUserByEmail(email);
		if (null == trueUser) {
			isUserExists = "No";
		}
		return isUserExists;
	}

	public User findUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
		User verifiedUser = userRepo.findUserByEmailAndPassword(email, password);
		if (null == verifiedUser) {
			throw new UserNotFoundException("Invalid Login Credentials");
		}
		return verifiedUser;
	}

	@Override
	public String resetPassword(String password, String email) throws UserNotFoundException {
		String message = "Password Reset Failed";
		User user = userRepo.findUserByEmail(email);
		if (null != user) {
			userRepo.setPasswordFor(password, email);
			message = "Password reset successful";
		} else if (null == user) {
			throw new UserNotFoundException("User not Found");
		}
		return message;
	}

	@Override
	public User findUserByEmail(String email) throws UserNotFoundException {
		User verifiedUser = userRepo.findUserByEmail(email);
		if (null == verifiedUser) {
			throw new UserNotFoundException("User does not exist");
		}
		return verifiedUser;
	}

}
