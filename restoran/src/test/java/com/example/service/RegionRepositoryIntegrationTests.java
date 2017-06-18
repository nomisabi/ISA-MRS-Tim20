package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Drink;
import com.example.domain.DrinkMenu;
import com.example.domain.Employee;
import com.example.domain.Offer;
import com.example.domain.Offer_status;
import com.example.domain.Region;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.Supply;
import com.example.domain.TableOfRestaurant;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.EmployeeRepository;
import com.example.respository.OfferRepository;
import com.example.respository.RegionRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.SupllierRepository;
import com.example.respository.SupplyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionRepositoryIntegrationTests {

	@Autowired
	RegionRepository repository;
	@Autowired
	RestaurantRepository restrepository;
	
	
	Region dm;
	Restaurant r;
	
	@Before
	public void setUp() {
		r= new Restaurant();
		r.setName("");
		r=restrepository.save(r);
		dm= new Region();
		dm.setName("");
		dm.setRestaurant(r);
		repository.save(dm);
	}

	@Test
	public void getRegion() {
		Region sFind = repository.findOne(dm.getId());
		assertNotNull(sFind);
	}

	
	@Test
	public void createRegion() {
		Region s1 = new Region();
		s1.setName("");
		s1.setRestaurant(r);
		Region savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteRegion() {
		Region s2 = new Region();
		s2.setName("");
		s2.setRestaurant(r);
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}
	
	@Test
	public void getRequests() {
		Collection<Region> sup= repository.getRequests(r.getId());
		assertEquals(1, sup.size());
	}
	
	@Test
	public void updateName() {
		repository.updateName(dm.getId(), "new");
		Region sFind = repository.findOne(dm.getId());
		assertEquals("new", sFind.getName());
	}

}
