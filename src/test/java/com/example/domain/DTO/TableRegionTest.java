package com.example.domain.DTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Region;
import com.example.domain.TableOfRestaurant;
import com.example.domain.DTOs.RateWaiter;
import com.example.domain.DTOs.TableRegion;

@RunWith(SpringRunner.class)
public class TableRegionTest {

	TableRegion rw;
	TableOfRestaurant t;
	Region r;

	@Before
	public void setUp(){
		t= new TableOfRestaurant();
		r= new Region();
		rw= new TableRegion(t, r);
	}
	
	@Test
	public void TestTable() {
		assertEquals(t, rw.getT());
	}
	
	@Test
	public void TestRegion() {
		assertEquals(r, rw.getR());
	}

	
	@Test
	public void TestTableRegion() {
		assertNotNull(rw);
	}
}
