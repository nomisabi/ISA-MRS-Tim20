package com.example.respository;

import java.util.Collection;

import com.example.domain.User;

public interface UserRepository {
	Collection<User> findAll();
	
	User findByEmail(String email);
	
	void setLogedIn(User u);
	
	User getLogedIn();
}
