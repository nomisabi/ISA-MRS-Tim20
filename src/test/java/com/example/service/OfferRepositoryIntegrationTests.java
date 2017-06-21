package com.example.service;

import static org.junit.Assert.assertEquals;
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
import com.example.domain.Supplier;
import com.example.domain.Supply;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.EmployeeRepository;
import com.example.respository.OfferRepository;
import com.example.respository.SupllierRepository;
import com.example.respository.SupplyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OfferRepositoryIntegrationTests {

	@Autowired
	OfferRepository repository;
	
	@Autowired
	SupplyRepository suprepository;

	Offer dm;
	Supply s;
	
	@Before
	public void setUp() {
		dm= new Offer();
		dm.setStatus(Offer_status.CHOOSED);
		dm = repository.save(dm);
		s= new Supply();
		Set<Offer> offers= s.getOffer();
		if (offers==null)
			offers= new HashSet<Offer>();
		offers.add(dm);
		s.setOffer(offers);	
		s.setFrom_date("");
		s.setName("");
		s.setTo_date("");
		s= suprepository.save(s);
		repository.updateName(dm.getId(), s.getId());
	}

	@Test
	public void getOffer() {
		Offer sFind = repository.findOne(dm.getId());
		assertNotNull(sFind);
	}

	
	@Test
	public void createOffer() {
		Offer s1 = new Offer();
		s1.setStatus(Offer_status.CHOOSED);
		Offer savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteOffer() {
		Offer s2 = new Offer();
		s2.setStatus(Offer_status.CHOOSED);
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}
	@Test
	public void updateStatus() {
		Offer s2 = new Offer();
		s2.setStatus(Offer_status.CHOOSED);
		repository.save(s2);
		repository.updateStatus(s2.getId(), 4);
		Offer updateS = repository.findOne(s2.getId());
		//System.out.println(updateS.toString());
		assertEquals(Offer_status.END, updateS.getStatus());
	}

	private static final double DELTA = 1e-15;
	@Test
	public void update() {
		Offer s2 = new Offer();
		s2.setStatus(Offer_status.CHOOSED);
		repository.save(s2);
		repository.update(s2.getId(), 4, 43.4);
		Offer updateS = repository.findOne(s2.getId());
		//System.out.println(updateS.toString());
		assertEquals(4, updateS.getQuality());
		double price= 43.4;
		assertEquals(price, updateS.getPrice(), DELTA);
	}

	@Test
	public void updateName() {
		Offer s2 = new Offer();
		s2.setStatus(Offer_status.CHOOSED);
		repository.save(s2);
		Supply s3= new Supply();
		Set<Offer> offers= new HashSet<Offer>();
		offers.add(s2);
		s3.setOffer(offers);	
		s3.setFrom_date("");
		s3.setName("");
		s3.setTo_date("");
		s3= suprepository.save(s3);
		repository.updateName(s2.getId(), s3.getId());
	
	}

}
