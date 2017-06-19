package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.EmployeeSchedule;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.System_manager;
import com.example.respository.SupllierRepository;
import com.example.respository.SystemManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierServiceIntegrationTest {
	@Autowired
	SupplierService service;
	
	@Autowired
	ManagerService mservice;
	
	@Autowired
	RestaurantService rservice;

	Supplier dm;
	Restaurant r;

	@Before
	public void setUp() {
		dm= new Supplier();
		dm = mservice.createSupplier(dm);
		Supplier d1= new Supplier();
		d1 = mservice.createSupplier(d1);
		r= new Restaurant();
		r.setName("r1");
		/*Set<Supplier> man;
		man= new java.util.HashSet<Supplier>();
		man.add(dm);
		man.add(d1);
		r.setSuppliers(man);*/
		r = rservice.createRestaurant(r);
		mservice.updateRest(r.getId(), dm.getId());
		mservice.updateRest(r.getId(), d1.getId());
	}


	@Test
	public void findAllSupplier() {
		assertEquals(8, service.findAll().size());
	}
	

	@Test
	public void findSupplier() {
		assertEquals(dm.getId(), service.findOne(dm.getId()).getId());
	}
	

	@Test
	public void isSupplierExist() {
		assertEquals(true, service.isSupplierExist(dm.getId()));
	}

	
	@Test
	public void getRest() {
		Collection<Restaurant> find= service.getRest(dm.getId());
		assertEquals( 1, find.size());
	}
	
	@Test
	public void updateSupplier() {
		Supplier d1= new Supplier();
		d1.setEmail("21");
		d1 = mservice.createSupplier(d1);
		d1.setEmail("123");
		service.update(d1);
		Supplier find= service.findOne(d1.getId());
		assertEquals( "123", find.getEmail());
	}
	
	@Test
	public void getSupplier() {
		Supplier dm= new Supplier();
		dm = mservice.createSupplier(dm);
		Supplier d1= new Supplier();
		d1 = mservice.createSupplier(d1);
		Restaurant r1= new Restaurant();
		r1.setName("r1");
		/*Set<Supplier> man;
		man= new java.util.HashSet<Supplier>();
		man.add(dm);
		man.add(d1);
		r.setSuppliers(man);*/
		r1 = rservice.createRestaurant(r);
		mservice.updateRest(r1.getId(), dm.getId());
		mservice.updateRest(r1.getId(), d1.getId());
		
		Collection<Supplier> find= service.getSupp(r1.getId());
		assertEquals( 11, find.size());
	}

	
}
