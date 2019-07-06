package com.shubhendu.moviecruiserauthentication.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shubhendu.moviecruiserauthentication.controller.UserController;
import com.shubhendu.moviecruiserauthentication.model.User;
import com.shubhendu.moviecruiserauthentication.service.SecurityTokenGenerator;
import com.shubhendu.moviecruiserauthentication.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private SecurityTokenGenerator securityTokenGenerator;
	
	private User user;
	
	@InjectMocks
	UserController userController;
	
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		user=new User("Charles231","Charles","Boyle","123456",new Date());
	}
	
	@Test
	public void testRegisterUser() throws Exception{
		when(userService.saveUser(user)).thenReturn(true);
		mockMVC.perform(post("/api/v1/userservice/register").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isCreated())
				.andDo(print());
		verify(userService,times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
		
		
	}
	
	@Test
	public void testLoginUser() throws Exception {
		String userId = "Charles231";
		String password = "123456";
		when(userService.saveUser(user)).thenReturn(true);
		when(userService.findByUserIdAndPassword(userId, password)).thenReturn(user);
		mockMVC.perform(post("/api/v1/userservice/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
		.andExpect(status().isOk());
		verify(userService,times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
		verifyNoMoreInteractions(userService);
	}
	
	private static String jsonToString(final Object obj) throws JsonProcessingException{
		String result;
		try{
			final ObjectMapper mapper= new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
			
		}catch(JsonProcessingException e){
			result = "Json Processing Error";
			
		}
		return result;
	}
	
	
	public UserControllerTest() {
		// TODO Auto-generated constructor stub
	}

}
