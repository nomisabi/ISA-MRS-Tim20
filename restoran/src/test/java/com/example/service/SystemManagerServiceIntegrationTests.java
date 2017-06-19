package com.example.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.EmployeeSchedule;
import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;
import com.example.respository.GuestRepository;
import com.example.respository.SystemManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemManagerServiceIntegrationTests {

	@Autowired
	SystemManagerService service;


	@Autowired
	ManagerService mservice;
	
	System_manager dm;
	Restaurant r;

	@Before
	public void setUp() {
		dm= new System_manager();
		dm.setEmail("123");
		dm.setPassword("123");
		dm = service.addSysMan(dm);
		r= new Restaurant();
		r.setName("r1");
		r= service.addRestaurant(r);
	}


	@Test
	public void findAllSysMan() {
		assertEquals(7, service.findAll().size());
	}
	

	
	@Test
	public void createSysMan() {
		System_manager d1= new System_manager();
		d1.setEmail("123");
		d1.setPassword("123");
		System_manager saved= service.addSysMan(d1);
		assertEquals(d1, saved);
	}
	
	

	@Test
	public void findSysMan() {
		//SysMan d1= new SysMan();
		//SysMan saved= service.createSysMan(d1);
		assertEquals(dm.getId(), service.findOne(dm.getId()).getId());
	}
	

	@Test
	public void isSysManExist() {
		System_manager d1= new System_manager();
		d1.setEmail("123");
		d1.setPassword("123");
		System_manager saved= service.addSysMan(d1);
		assertEquals(true, service.isSysManagerExist(d1.getId()));
	}

	
	@Test
	public void updateSysMan() {
		System_manager d1= new System_manager();
		d1.setEmail("12");
		d1.setPassword("123");
		System_manager saved= service.addSysMan(d1);
		d1.setEmail("123");
		service.update(d1);
		System_manager find= service.findOne(d1.getId());
		assertEquals( "123", find.getEmail());
	}


	@Test
	public void findAllRestaurant() {
		assertEquals(17, service.findAllRest().size());
	}
	

	
	@Test
	public void createRestaurant() {
		Restaurant d1= new Restaurant();
		d1.setName("123");
		Restaurant saved= service.addRestaurant(d1);
		assertEquals(d1, saved);
	}
	
	

	@Test
	public void findRestaurant() {
		assertEquals(r.getId(), service.findOneRest(r.getId()).getId());
	}
	

	@Test
	public void isRestaurantExist() {
		Restaurant d1= new Restaurant();
		d1.setName("123");
		Restaurant saved= service.addRestaurant(d1);
		assertEquals(true, service.isRestaurantExist(d1.getId()));
	}

	
	@Test
	public void updateRestaurant() {
		Restaurant d1= new Restaurant();
		d1.setName("13");
		Restaurant saved= service.addRestaurant(d1);
		d1.setName("123");
		Restaurant find=  service.update(d1);
		//Restaurant find= service.findOneRest(saved.getId());
		assertEquals( "123", find.getName());
	}
	
	@Test
	public void insertManager() {
		Manager d1= new Manager();
		d1.setEmail("123");
		d1.setPassword("123");
		d1 = mservice.createManager(d1);
		Restaurant r1= new Restaurant();
		r1.setName("13");
		Restaurant saved= service.addRestaurant(r1);
		service.insertManager(saved.getId(), d1.getId());
		
		Manager find= mservice.findOne(d1.getId());
		Restaurant rFind= mservice.findRest(find.getId());
		assertEquals( r1.getId(), rFind.getId());
	}


}
