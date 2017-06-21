package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.hibernate.annotations.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.TypeOfUser;
import com.example.domain.User;
import com.example.respository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIntegrationTest {

	@Autowired
	UserRepository repository;

	User u;

	@Before
	public void setUp() {
		u = new User("admin@admin.com", "admin", TypeOfUser.SYS_MAN);
		u = repository.save(u);

	}

	@Test
	public void getUser() {
		User uFind = repository.findOne(u.getId());
		assertNotNull(uFind);
	}

	@Test
	public void createUser() {
		User user = new User("proba@proba.com", "pass", TypeOfUser.MANAGER);
		User savedUser = repository.save(user);
		assertEquals(user, savedUser);
	}

	@Test
	public void deleteUser() {
		repository.delete(u.getId());
		assertNull(repository.findOne(u.getId()));
	}

}
