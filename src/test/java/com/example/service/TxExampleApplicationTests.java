package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Offer;
import com.example.domain.Offer_status;
import com.example.domain.Supply;
import com.example.respository.SupplyRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TxExampleApplicationTests {
	
	@Autowired
	private OfferSupplyService osService;
	
	@Autowired
	private SupplyRepository srep;


	@Before
	public void setUp() throws Exception {
		Supply s= new Supply("ime", "frfom", "to", false);
		osService.createSupply(s);
		osService.createOffer(new Offer(3, 2, Offer_status.WAITING), s);
		
	}

	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void update() {

		Supply productForUserOne = osService.findSupply(1L);
		Offer offer1 = osService.findOffer(1L);
		Offer offer2 = osService.findOffer(1L);
		Supply old = productForUserOne;

		//verzija oba objekta je 0
	//	assertEquals(0, productForUserOne.getVersion().intValue());
	//	assertEquals(0, old.getVersion().intValue());

		//pokusaj cuvanja prvog objekta
		osService.update(productForUserOne, offer1);
		Supply find = osService.findSupply(1L);
		//assertEquals(1, find.getVersion().intValue());
		osService.update(productForUserOne, offer1);
	}
	
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void createOffer() {

		Supply productForUserOne = osService.findSupply(1L);
		Offer o1= new Offer(3, 2, Offer_status.WAITING);
		osService.createOffer(o1, productForUserOne);			
		osService.createOffer(new Offer(3, 2, Offer_status.WAITING), productForUserOne);
		assertEquals(false, productForUserOne.isChosed());
		osService.update(productForUserOne, o1);
		//assertEquals(false, productForUserOne.isChosed());
		osService.createOffer(new Offer(3, 2, Offer_status.WAITING), productForUserOne);
		
	}
	
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void updateOfferQualityAndPrice() {

		Supply productForUserOne = osService.findSupply(1L);
		Offer o1= new Offer(3, 2, Offer_status.WAITING);
		osService.updateOfferQualityAndPrice(o1, productForUserOne);			
		osService.updateOfferQualityAndPrice(new Offer(3, 2, Offer_status.WAITING), productForUserOne);
		//assertEquals(false, productForUserOne.isChosed());
		osService.update(productForUserOne, o1);
		osService.updateOfferQualityAndPrice(new Offer(3, 2, Offer_status.WAITING), productForUserOne);		
	}

}
