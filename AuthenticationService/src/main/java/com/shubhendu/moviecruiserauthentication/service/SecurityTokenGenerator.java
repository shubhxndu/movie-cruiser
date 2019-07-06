package com.shubhendu.moviecruiserauthentication.service;

import java.util.Map;

import com.shubhendu.moviecruiserauthentication.model.User;

public interface SecurityTokenGenerator {
	
	Map<String,String> generateToken(User user);

}
