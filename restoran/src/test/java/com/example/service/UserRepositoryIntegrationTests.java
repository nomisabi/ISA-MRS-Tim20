package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Drink;
import com.example.domain.DrinkMenu;
import com.example.domain.Employee;
import com.example.domain.Supplier;
import com.example.domain.TypeOfUser;
import com.example.domain.User;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.EmployeeRepository;
import com.example.respository.SupllierRepository;
import com.example.respository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIntegrationTests {

	@Autowired
	UserRepository repository;

	User dm;

	@Before
	public void setUp() {
		dm= new User("email", "password", TypeOfUser.MANAGER);
		dm = repository.save(dm);
	}

	@Test
	public void getUser() {
		User sFind = repository.findOne(dm.getId());
		assertNotNull(sFind);
	}

	@Test
	public void createUser() {
		User s1 = new User("email", "password", TypeOfUser.MANAGER);
		User savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteUser() {
		User s2 = new User("email", "password", TypeOfUser.MANAGER);
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}
	
	@Test
	public void updateUser() {
		repository.updateGuest(dm.getId(), "123");
		repository.setPassword(dm.getId(), "123");
		User u= repository.findOne(dm.getId());
		assertEquals("123", u.getEmail());
		assertEquals("123", u.getPassword());
	}

}
