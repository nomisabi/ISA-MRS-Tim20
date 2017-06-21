package com.example.domain.DTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.DTOs.SupplierRestaurant;

@RunWith(SpringRunner.class)
public class SupplierRestTest {

	SupplierRestaurant dr;
	Supplier d;
	Restaurant r;
	
	@Before
	public void setUp(){
		d= new Supplier();
		r= new Restaurant();
		dr= new SupplierRestaurant(r, d);
	}
	
	@Test
	public void TestSupplierMenu() {
		assertEquals(d, dr.getS());
	}
	
	@Test
	public void TestRest() {
		assertEquals(r, dr.getR());
	}
	
	@Test
	public void TestSupplierRestaurant() {
		assertNotNull(dr);;
	}
}
