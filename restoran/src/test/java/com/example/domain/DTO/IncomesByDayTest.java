package com.example.domain.DTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.DTOs.IncomesByDay;

@RunWith(SpringRunner.class)
public class IncomesByDayTest {

	IncomesByDay id;
	
	@Before
	public void setUp(){
		id=new IncomesByDay("day",4.3);
	}
	
	@Test
	public void TestName() {
		assertEquals("day", id.getDay());
	}
	
	@Test
	public void TestPrice() {
		assertEquals(4,3 , id.getPrice());
	}
	
	@Test
	public void TestFoodRate() {
		assertNotNull(id);
	}
}
