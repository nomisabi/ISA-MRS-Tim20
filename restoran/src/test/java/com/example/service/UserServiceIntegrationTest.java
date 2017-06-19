package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.hibernate.annotations.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.User;
import com.example.domain.TypeOfUser;
import com.example.domain.User;
import com.example.respository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

	@Autowired
	UserService service;
	
	@Autowired
	UserRepository repository;

	User dm;

	@Before
	public void setUp() {
		dm= new User();
		dm.setEmail("email");
		dm.setPassword("pass");
		dm = service.addUser(dm);
	}


	@Test
	public void findAllUser() {
		assertEquals(14, service.findAll().size());
	}
	

	
	@Test
	public void createUser() {
		User d1= new User();
		d1.setEmail("email");
		d1.setPassword("pass");
		User saved= service.addUser(d1);
		assertEquals(d1, saved);
	}
	
	
	@Test
	public void changePass() {
		User d1= new User();
		d1.setEmail("email");
		d1.setPassword("pass");
		User saved= service.addUser(d1);
		service.changePassword(saved.getId(), "new");
		User find = repository.findOne(saved.getId());
		assertEquals("new",find.getPassword());
	}
	
	@Test
	public void updateEmail() {
		User d1= new User();
		d1.setEmail("email");
		d1.setPassword("pass");
		User saved= service.addUser(d1);
		service.updateEmail(saved.getId(), "new");
		User find = repository.findOne(saved.getId());
		assertEquals("new",find.getEmail());
	}
	

	@Test
	public void loginAndGetLoginAndLogout() {
		service.login(dm);
		User login = service.getLogin();
		assertEquals(dm.getId(), login.getId());
		service.logout();
		login = service.getLogin();
		assertNull(login);
	}
	
	

}
