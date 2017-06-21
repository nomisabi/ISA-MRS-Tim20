package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Offer;
import com.example.domain.Offer_status;
import com.example.domain.Region;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.Supply;
import com.example.domain.System_manager;
import com.example.domain.TableOfRestaurant;
import com.example.respository.OfferRepository;
import com.example.respository.RegionRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.SupllierRepository;
import com.example.respository.SupplyRepository;
import com.example.respository.SystemManagerRepository;
import com.example.respository.TableOfRestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableOfRestaurantRepositoryIntegrationTest {

	@Autowired
	TableOfRestaurantRepository repository;
	
	@Autowired
	RestaurantRepository rrepository;
	
	@Autowired
	RegionRepository rgrepository;


	TableOfRestaurant t;
	Restaurant r;
	Region rg;
	
	@Before
	public void setUp() {
		t= new TableOfRestaurant();
		t.setNumber(3);
		r= new Restaurant();
		r.setName("");
		t.setRestaurant(r);
		rrepository.save(r);
		repository.save(t);
	}

	@Test
	public void getSupply() {
		TableOfRestaurant sFind = repository.findOne(t.getId());
		assertNotNull(sFind);
	}

	@Test
	public void createTableOfRestaurant() {
		TableOfRestaurant s1 = new TableOfRestaurant();

		TableOfRestaurant savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteTableOfRestaurant() {
		TableOfRestaurant s2 = new TableOfRestaurant();
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}

	@Test
	public void getRequests() {
		Collection<TableOfRestaurant> sup= repository.getTableOfRestaurant(r.getId());
		assertEquals(1, sup.size());
	}
	
	@Test
	public void insertRegAndgetTablesByRegion() {
		rg= new Region();
		rg.setName("");
		rg.setRestaurant(r);
		rgrepository.save(rg);
		repository.insertReg(t.getId(), rg.getId());
		Collection<TableOfRestaurant> tabl=  repository.getTablesByRegion(rg.getId());
		assertEquals(1, tabl.size());
	}
	

	@Test
	public void getByNum() {
		TableOfRestaurant sup= repository.getByNum(3,r.getId());
		assertEquals(t.getId(), sup.getId());
	}

}
