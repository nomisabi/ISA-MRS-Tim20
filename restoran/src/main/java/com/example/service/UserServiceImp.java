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
import com.example.respository.SystemManagerRepository;
import com.example.respository.UserRepository;

@Service
public class UserServiceImp implements UserService{
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UserRepository userRepository;

	
	@Override
	public Collection<User> findAll() {
		logger.info("> findAll");
		Collection<User> users = (Collection<User>) userRepository.findAll();
		if (users.isEmpty())
			logger.info("empty");
		logger.info("< findAll");
		return users;
	}
	
	@Override
	public User findByEmail(String email) {
		logger.info("> findByEmail");
		//User user= userRepository.findByEmail(email);
		logger.info("< findByEmail");
		//return user;
		return null;
	}

	@Override
	public User addUser(User u) {
		logger.info("> savev");
		User user= userRepository.save(u);
		logger.info("< save");
		return user;
		//return null;
	}

	@Override
	public User changePass(User old, User pass) {
		userRepository.delete(old);
		return userRepository.save(pass);
	}


	
}
