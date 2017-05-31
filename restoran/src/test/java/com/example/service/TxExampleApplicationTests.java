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


@RunWith(SpringRunner.class)
@SpringBootTest
public class TxExampleApplicationTests {
	
	@Autowired
	private OfferSupplyService osService;

	@Before
	public void setUp() throws Exception {
		Supply s= new Supply("ime", "frfom", "to", false);
		osService.createSupply(s);
		osService.createOffer(new Offer(3, 2, Offer_status.WAITING), s);
		
	}

	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testConcurrencyWriting() {

		Supply productForUserOne = osService.findSupply(1L);
		Offer productForUserTwo = osService.findOffer(1L);
		Supply old = productForUserOne;
		//modifikovanje istog objekta
		productForUserOne.setChosed(true);

		//verzija oba objekta je 0
		assertEquals(0, productForUserOne.getVersion().intValue());
		assertEquals(0, old.getVersion().intValue());

		//pokusaj cuvanja prvog objekta
		osService.updateSupp(productForUserOne);
		//osService.update(productForUserOne, productForUserTwo);
		Supply productForUserTree = osService.findSupply(1L);
		assertEquals(1, productForUserTree.getVersion().intValue());
		osService.updateSupp(old);
		//assertEquals(1, productForUserOne.getVersion().intValue());
		//pokusaj cuvanja drugog objekta - Exception!
		//osService.updateOfferQualityAndPrice(productForUserTwo, old);
	}

}
