package com.example.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserTest {
	
	User u;

	@Before
	public void setUp(){
		u= new User("email", "password", TypeOfUser.EMPLOYEE);
		u.setId((long)1);
	}
	
	@Test
	public void testGetEmail(){
		assertEquals("email", u.getEmail());
	}
	
	@Test
	public void testGetPassword(){
		assertEquals("password", u.getPassword());
	}
	
	@Test
	public void testGetType(){
		assertEquals(TypeOfUser.EMPLOYEE, u.getType());
	}
	
	@Test
	public void testGetId(){
		assertEquals((long)1, u.getId());
	}
}
