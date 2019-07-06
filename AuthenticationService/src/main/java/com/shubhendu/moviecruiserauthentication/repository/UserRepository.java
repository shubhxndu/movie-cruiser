package com.shubhendu.moviecruiserauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubhendu.moviecruiserauthentication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	User findByUserIdAndPassword(String userId, String password);

}
