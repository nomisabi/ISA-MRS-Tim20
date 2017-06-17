package com.example.domain;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ScheduleTest {
	
	Schedule s;
	Set<Region> r;
	
	@Before
	public void setUp(){
		r= new HashSet<Region>();
		s= new Schedule((long) 1, r);
	}
	
	@Test
	public void TestId() {
		assertEquals((long) 1, s.getId());
	}

	@Test
	public void TestRegions() {
		assertEquals(r, s.getRegions());
	}
	


}
