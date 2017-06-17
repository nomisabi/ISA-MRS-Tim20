package com.example.domain.DTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Employee;
import com.example.domain.DTOs.IncomesByDay;
import com.example.domain.DTOs.IncomesByWaiters;

@RunWith(SpringRunner.class)
public class IncomesByWaiterTest {

	IncomesByWaiters id;
	
	@Before
	public void setUp(){
		id=new IncomesByWaiters("firstName", "lastName", 4.4);
	}
	
	@Test
	public void TestName() {
		assertEquals("firstName", id.getFirstName());
	}
	
	@Test
	public void TestLasrName() {
		assertEquals("lastName", id.getLastName());
	}
	
	@Test
	public void TestPrice() {
		assertEquals(4,4 , id.getPrice());
	}
	
	@Test
	public void TestFoodRate() {
		assertNotNull(id);
	}
}
