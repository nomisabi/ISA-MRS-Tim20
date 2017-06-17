package com.example.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmployeeScheduleTest {

	EmployeeSchedule es;
	Employee e;
	Date day;
	Schedule s;

	@Before
	public void setUp(){
		e = new Employee();
		day= new Date();
		s= new Schedule();
		es= new EmployeeSchedule((long)1, e, day, "st", "en", "c1", "c2", s);
	}
	
	@Test
	public void TestId() {
		assertEquals((long) 1, es.getId());
	}

	@Test
	public void TestEmployee() {
		assertEquals(e, es.getEmployee());
	}
	
	@Test
	public void TestDay() {
		assertEquals(day, es.getDay());
	}
	
	@Test
	public void TestStart() {
		assertEquals("st", es.getStartTime());
	}

	@Test
	public void TestEnd() {
		assertEquals("en", es.getEndTime());
	}
	
	@Test
	public void TestC1() {
		assertEquals("c1", es.getC1());
	}
	
	@Test
	public void TestC2() {
		assertEquals("c2", es.getC2());
	}

	@Test
	public void TestSchedule() {
		assertEquals(s, es.getSchedule());
	}
	

}
