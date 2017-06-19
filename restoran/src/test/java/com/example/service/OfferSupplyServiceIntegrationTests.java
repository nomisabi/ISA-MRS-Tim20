package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Drink;
import com.example.domain.DrinkMenu;
import com.example.domain.EmployeeSchedule;
import com.example.domain.Offer;
import com.example.domain.Offer_status;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.Supply;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.EmployeeScheduleRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.SupllierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OfferSupplyServiceIntegrationTests {

	@Autowired
	OfferSupplyService service;
	
	@Autowired
	RestaurantRepository rep;
	
	@Autowired
	SupllierRepository srep;


	Supply dm;
	Offer offer;
	Restaurant rest;
	Supplier sup;

	@Before
	public void setUp() {
		dm= new Supply();
		dm.setFrom_date("123");
		dm.setName("123");
		dm.setTo_date("");
		dm.setChosed(true);
		Restaurant r  =new Restaurant();
		r.setName("");
		r= rep.save(r);
		dm.setRestaurant(r);
		rest=r;
		dm = service.createSupply(dm);
		
		offer= new Offer();
		offer.setStatus(Offer_status.WAITING);
		sup=new Supplier();
		sup.setEmail("");
		sup= srep.save(sup);
		
		offer.setSupplier(sup);
		offer= service.createOffer(offer, dm);
	}

	@Test
	public void findAllSupply() {
		assertNotEquals(0, service.findAllSupply().size());
	}
	
	
	@Test
	public void createSupply() {
		Supply d1= new Supply();
		d1.setFrom_date("123");
		d1.setName("123");
		d1.setTo_date("");
		Supply saved= service.createSupply(d1);
		assertEquals(d1, saved);
	}
	
	
	@Test
	public void deleteSupply() {
		Supply d1= new Supply();
		d1.setFrom_date("123");
		d1.setName("123");
		d1.setTo_date("");
		Supply saved= service.createSupply(d1);
		service.deleteSupply(saved.getId());
		assertNull(service.findSupply(saved.getId()));
	}

	@Test
	public void findSupply() {
		assertEquals(dm.getId(), service.findSupply(dm.getId()).getId());
	}
	

	@Test
	public void isSupplyExist() {
		Supply d1= new Supply();
		d1.setFrom_date("123");
		d1.setName("123");
		d1.setTo_date("");
		Supply saved= service.createSupply(d1);
		assertEquals(true, service.isSupplyExist(d1.getId()));
	}

	
	@Test
	public void updateSupply() {
		Supply d1= new Supply();
		d1.setFrom_date("123");
		d1.setName("1233");
		d1.setTo_date("");
		Supply saved= service.createSupply(d1);
		service.updateSupp(d1);
		Supply find= service.findSupply(d1.getId());
		assertEquals( true, d1.isChosed());
	}


	@Test
	public void findAllOffer() {
		assertNotEquals(0, service.findAllOffer().size());
	}
	
	
	@Test
	public void createOffer() {
		Supply dm2= new Supply();
		dm2.setFrom_date("123");
		dm2.setName("123");
		dm2.setTo_date("");
		dm2.setChosed(true);
		dm2 = service.createSupply(dm2);
		Offer d1= new Offer();
		d1.setStatus(Offer_status.WAITING);
		Offer saved= offer= service.createOffer(d1, dm2);
		assertEquals(d1, saved);
	}
	
	
	@Test
	public void deleteOffer() {
		Supply dm2= new Supply();
		dm2.setFrom_date("123");
		dm2.setName("123");
		dm2.setTo_date("");
		dm2.setChosed(true);
		dm2 = service.createSupply(dm2);
		Offer d1= new Offer();
		d1.setStatus(Offer_status.WAITING);
		Offer saved= offer= service.createOffer(d1, dm2);
		service.deleteOffer(saved.getId());
		assertNull(service.findOffer(saved.getId()));
	}

	@Test
	public void findOffer() {
		assertEquals(offer.getId(), service.findOffer(offer.getId()).getId());
	}
	

	@Test
	public void isOfferExist() {
		assertEquals(true, service.isOfferExist(offer.getId()));
	}

	
	@Test
	public void updateOffer() {		
		service.updateOffer(offer);
		Offer find= service.findOffer(offer.getId());
		assertEquals(Offer_status.SEND, find.getStatus());
	}
	
	@Test
	public void updateOfferToEnd() {		
		service.updateOfferToEnd(offer);
		Offer find= service.findOffer(offer.getId());
		assertEquals(Offer_status.END, find.getStatus());
	}
	
	
	@Test
	public void updateOfferQualityAndPrice() {	
		Supply dm2= new Supply();
		dm2.setFrom_date("123");
		dm2.setName("123");
		dm2.setTo_date("");
		dm2.setChosed(true);
		dm2 = service.createSupply(dm2);
		Offer d1= new Offer();
		d1.setStatus(Offer_status.WAITING);
		Offer saved= service.createOffer(d1, dm2);
		Supply s= service.findSupply(dm2.getId());
		saved.setQuality(3);
		service.updateOfferQualityAndPrice(saved, s);
		Offer find= service.findOffer(saved.getId());
		assertEquals(3, find.getQuality());
	}
	
	@Test
	public void getSupplyByRest() {		
		assertEquals(1, service.getSupplyByRest(rest.getId()).size());
		assertEquals(0, service.getSupplyByRestChoosed(rest.getId()).size());
	}
	
	@Test
	public void getSupply() {		
		assertEquals(0, service.getWaitingSupply(sup.getId()).size());
		assertEquals(0, service.getNotChosedSupply(sup.getId()).size());
		assertEquals(1, service.getSupplyWithMyOffer(sup.getId()).size());
	}
}
