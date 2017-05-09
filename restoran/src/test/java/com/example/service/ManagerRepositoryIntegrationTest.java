package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;
import com.example.respository.ManagerRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.SystemManagerRepository;

import scala.collection.mutable.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerRepositoryIntegrationTest {
	@Autowired
	ManagerRepository repository;
	
	@Autowired
	RestaurantRepository restRepository;

	Manager m;
	//Restaurant r;

	@Before
	public void setUp() {
		
		m = new Manager("a@a.a","pass","a", "a");
		m.setActive(false);		
		m = repository.save(m);
		//r= new Restaurant("name", "lok");
		//Set<Manager> man =  r.getManager();
		//man.add(m);
		//r.setManager(man);
		//restRepository.save(r);
	}

	@Test
	public void getManager() {
		Manager mFind = repository.findOne(m.getId());
		assertNotNull(mFind);
	}

	@Test
	public void createManager() {
		m = new Manager("a_new@a.a","pass","a", "a");
		Manager savedM = repository.save(m);
		assertEquals(m, savedM);
	}

	@Test
	public void deleteManager() {
		m = new Manager("a_del@a.a","pass","a", "a");
		repository.save(m);
		repository.delete(m.getId());
		assertNull(repository.findOne(m.getId()));
	}

	@Test
	public void updateManager() {
		repository.updatePass(m.getId(), m.getPassword(), "newmail@gmail.com", m.getFirstName(), "lastName", true);
		Manager updateM = repository.findOne(m.getId());
		assertEquals("newmail@gmail.com", updateM.getEmail());
		assertEquals("a", updateM.getFirstName());
		assertEquals("lastName", updateM.getLastName());
		assertEquals("pass", updateM.getPassword());
		assertEquals(true, updateM.isActive());
	}
	
	/*@Test
	public void getRestaurantOfManager() {
		long rest_id=repository.getRest(m.getId());
		assertEquals(rest_id, r.getId());
	}*/
}
