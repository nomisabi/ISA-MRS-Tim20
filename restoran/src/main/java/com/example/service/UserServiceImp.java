package com.example.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;
import com.example.domain.User;
import com.example.respository.SystemManRepository;
import com.example.respository.SystemManagerRepository;
import com.example.respository.UserRepositoryImpl;

@Service
public class UserServiceImp implements UserService{
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UserRepositoryImpl userRepository;

	
	@Override
	public Collection<User> findAll() {
		logger.info("> findAll");
		//Collection<User> users = userRepository.findAll();
		logger.info("< findAll");
		//return users;
		return null;
	}
	
	@Override
	public User findByEmail(String email) {
		logger.info("> findByEmail");
		//User user= userRepository.findByEmail(email);
		logger.info("< findByEmail");
		//return user;
		return null;
	}



	
}
