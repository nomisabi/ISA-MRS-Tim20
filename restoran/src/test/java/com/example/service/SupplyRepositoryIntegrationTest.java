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
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.Supply;
import com.example.domain.System_manager;
import com.example.respository.OfferRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.SupllierRepository;
import com.example.respository.SupplyRepository;
import com.example.respository.SystemManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplyRepositoryIntegrationTest {

	@Autowired
	SupplyRepository repository;
	
	@Autowired
	RestaurantRepository rrepository;
	
	@Autowired
	OfferRepository orepository;
	@Autowired
	SupllierRepository srepository;

	Supply s;
	Restaurant r;
	Supplier sup; 
	
	@Before
	public void setUp() {
		sup= new Supplier();
		sup = srepository.save(sup);
		s = new Supply();
		s.setFrom_date("");
		s.setName("");
		s.setTo_date("");
		s.setChosed(false);
		r= new Restaurant();
		r.setName("");
		r= rrepository.save(r);
		Set<Offer> offers = new HashSet<Offer>();
		Offer o= new Offer();
		o.setStatus(Offer_status.WAITING);
		o.setSupplier(sup);
		o = orepository.save(o);
		System.out.println("O:"+o.getSupplier().getId());
		offers.add(o);
		Offer o2= new Offer();
		o2.setStatus(Offer_status.NOT_CHOOSED);
		o2.setSupplier(sup);
		o2 = orepository.save(o2);
		System.out.println("O2:"+o2.getSupplier().getId());
		offers.add(o2);
		
		s.setOffer(offers);
		s.setRestaurant(r);
		s = repository.save(s);
	}

	@Test
	public void getSupply() {
		Supply sFind = repository.findOne(s.getId());
		assertNotNull(sFind);
	}

	@Test
	public void createSupply() {
		Supply s1 = new Supply();
		s1.setFrom_date("");
		s1.setName("");
		s1.setTo_date("");	
		Supply savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteSupply() {
		Supply s2 = new Supply();
		s2.setFrom_date("");
		s2.setName("");
		s2.setTo_date("");
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}
	
	@Test
	public void getSupplyByRest() {
		Collection<Supply> sup= repository.getSupplyByRest(r.getId());
		assertEquals(1, sup.size());
	}
	
	@Test
	public void updateStatus() {
		repository.updateStatus(s.getId(), true);
		s= repository.findOne(s.getId());
		assertEquals(true, s.isChosed());
	}
	
	@Test
	public void getSupplyByRestChoosed() {
		
		Collection<Supply> sup= repository.getSupplyByRest(r.getId());
		assertEquals(1, sup.size());
	}
	
	@Test
	public void getWatingSupply() 
	{	
		Collection<Supply> sup= repository.getWatingSupply(1L);
		assertEquals(1, sup.size());
	}
	
	@Test
	public void getNotChosedSupply() 
	{
		long id= sup.getId();
		Collection<Supply> sup= repository.getNotChosedSupply(3L);
		assertEquals(0, sup.size());
	}
	
	
	@Test
	public void getSupplyWithMyOffer() 
	{
		long id= sup.getId();
		System.out.println("offer:"+id);
		Collection<Supply> sup= repository.getSupplyWithMyOffer(1L);
		assertEquals(2, sup.size());
	}
}
