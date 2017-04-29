package com.example.respository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;
import com.example.domain.TypeOfUser;
import com.example.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	private static AtomicLong counter = new AtomicLong();
	
	private User logIn=null;
	private final ConcurrentMap<String, User> users = new ConcurrentHashMap<String, User>();

	@Override
	public Collection<User> findAll() {
		User sm = new User("1","2", TypeOfUser.SYS_MAN);
		users.put("1", sm);
		return this.users.values();
	}

	@Override
	public User findByEmail(String email){
		User sm = new User("1","2", TypeOfUser.SYS_MAN);
		User sm2 = new User("2","2", TypeOfUser.GUEST);
		users.put("1", sm);
		users.put("2", sm2);
		for (User u : users.values()) {
			if (u.getEmail().equalsIgnoreCase(email)){
				return u;
			}
		}
		return null;
	}

	@Override
	public void setLogedIn(User u){
		logIn=u;
	}
	
	@Override
	public User getLogedIn(){
		return logIn;
	}
}
