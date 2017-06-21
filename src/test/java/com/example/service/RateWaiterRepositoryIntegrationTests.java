package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import com.example.domain.RateWaiter;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.Supply;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.EmployeeRepository;
import com.example.respository.OfferRepository;
import com.example.respository.RateWaiterRepository;
import com.example.respository.SupllierRepository;
import com.example.respository.SupplyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RateWaiterRepositoryIntegrationTests {

	@Autowired
	RateWaiterRepository repository;

	RateWaiter s;
	
	@Before
	public void setUp() {
		s= new RateWaiter((long)1, new Employee(), new Restaurant(), 3);
	}

	@Test
	public void getRW() {
		RateWaiter sFind = repository.findOne(s.getId());
		assertNotNull(sFind);
	}

	@Test
	public void createRW() {
		RateWaiter s1 = new RateWaiter();
		RateWaiter savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteOffer() {
		RateWaiter s2 = new RateWaiter();
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}
	
	@Test
	public void getAvgByWaiter() {
		RateWaiter s2 = new RateWaiter();
		repository.save(s2);
		repository.delete(s2.getId());
		assertNotEquals(repository.getAvgByWaiter(1L), 0);
	}


}
