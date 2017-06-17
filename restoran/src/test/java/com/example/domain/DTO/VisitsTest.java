package com.example.domain.DTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.DTOs.IncomesByDay;
import com.example.domain.DTOs.Visits;
@RunWith(SpringRunner.class)
public class VisitsTest {

	Visits id;
	
	@Before
	public void setUp(){
		id=new Visits("day",4);
	}
	
	@Test
	public void TestDay() {
		assertEquals("day", id.getDay());
	}
	
	@Test
	public void TestVisit() {
		assertEquals(4 , id.getNumb());
	}
	
	@Test
	public void TestVisits() {
		assertNotNull(id);
	}
}
