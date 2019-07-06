package com.shubhendu.moviecruiserauthentication.service;

import com.shubhendu.moviecruiserauthentication.exception.UserAlreadyExistsException;
import com.shubhendu.moviecruiserauthentication.exception.UserNotFoundException;
import com.shubhendu.moviecruiserauthentication.model.User;

public interface UserService {

	boolean saveUser(User user) throws UserAlreadyExistsException,UserNotFoundException;
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
