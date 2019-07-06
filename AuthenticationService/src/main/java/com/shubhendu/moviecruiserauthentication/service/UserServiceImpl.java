package com.shubhendu.moviecruiserauthentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhendu.moviecruiserauthentication.exception.UserAlreadyExistsException;
import com.shubhendu.moviecruiserauthentication.exception.UserNotFoundException;
import com.shubhendu.moviecruiserauthentication.model.User;
import com.shubhendu.moviecruiserauthentication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException, UserNotFoundException {
		
		Optional<User> fetchedUser = userRepository.findById(user.getUserId());
		if(fetchedUser.isPresent()){
			throw new UserAlreadyExistsException("User with this ID already exists");
			
		}
		userRepository.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		
		User user = userRepository.findByUserIdAndPassword(userId, password);
		if(user == null){
			throw new UserNotFoundException("User ID and password mismatch");
		}
		return user;
	}

}
