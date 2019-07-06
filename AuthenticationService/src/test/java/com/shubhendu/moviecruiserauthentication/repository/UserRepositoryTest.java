package com.shubhendu.moviecruiserauthentication.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shubhendu.moviecruiserauthentication.model.User;
import com.shubhendu.moviecruiserauthentication.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	@Before
	public void setUp() throws Exception{
		user = new User("John","John jennny","Bob","123456",new Date());
	}
	
	@Test
	public void testRegisterUserSucess(){
		userRepository.save(user);
		Optional<User> object = userRepository.findById(user.getUserId());
		assertThat(object.equals(user));
	}
	
	
	public UserRepositoryTest() {
		// TODO Auto-generated constructor stub
	}

}
