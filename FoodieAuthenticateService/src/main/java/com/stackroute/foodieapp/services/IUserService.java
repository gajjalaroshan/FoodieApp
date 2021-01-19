package com.stackroute.foodieapp.services;

import com.stackroute.foodieapp.exceptions.OldUserException;
import com.stackroute.foodieapp.exceptions.UserNotFoundException;
import com.stackroute.foodieapp.model.User;

public interface IUserService {

	boolean saveUser(User user) throws OldUserException, UserNotFoundException;

	boolean updateUser(User user) throws OldUserException, UserNotFoundException;

	User findUserByEmailAndPassword(String userId, String password) throws UserNotFoundException;

	String isUserExists(String email);

	String resetPassword(String newPassword, String email) throws UserNotFoundException;

	User findUserByEmail(String email) throws UserNotFoundException;

}
