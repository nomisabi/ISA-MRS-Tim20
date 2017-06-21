package com.example.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BillTest {

	Bill b;
	Date date;
	Employee e;
	
	@Before
	public void setUp(){
		e= new Employee();
		date= new Date();
		Long id=(long) 1;
		b = new Bill(id, e, 5.5, date.toString());
	}
	
	@Test
	public void TestId() {
		Long id=(long) 1;
		assertEquals(id, b.getId());
	}
	
	@Test
	public void TestEmployee() {
		assertEquals(e, b.getEmployee());
	}

	@Test
	public void TestPrice() {
		assertEquals(5,5, b.getPrice());
	}
	
	@Test
	public void TestDate() {
		assertEquals(date.toString(), b.getDate());
	}
}
