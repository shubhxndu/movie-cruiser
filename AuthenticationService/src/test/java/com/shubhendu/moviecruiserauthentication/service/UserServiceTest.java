package com.shubhendu.moviecruiserauthentication.service;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.shubhendu.moviecruiserauthentication.exception.UserAlreadyExistsException;
import com.shubhendu.moviecruiserauthentication.exception.UserNotFoundException;
import com.shubhendu.moviecruiserauthentication.model.User;
import com.shubhendu.moviecruiserauthentication.repository.UserRepository;
import com.shubhendu.moviecruiserauthentication.service.UserServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	private User user;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	Optional<User> options;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		user=new User("jake322","Jake","Peralta","123456",new Date());
		options = Optional.of(user);
	}
	
	@Test
	public void testSaveUserSuccess() throws UserNotFoundException,UserAlreadyExistsException{
		when(userRepository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		assertEquals("Cannot Register User",true,flag);
		verify(userRepository,times(1)).save(user);
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	public void testSaveUserFailure() throws UserNotFoundException,UserAlreadyExistsException{
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		
	}
	
	@Test
	public void testValidateSuccess() throws UserNotFoundException {
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals("jake322",userResult.getUserId());
		verify(userRepository,times(1)).findByUserIdAndPassword(user.getUserId(),user.getPassword());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testValidateFailure() throws UserNotFoundException {
		when(userRepository.findById("jake")).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(),user.getPassword());
	}
	
	public UserServiceTest() {
		// TODO Auto-generated constructor stub
	}

}
