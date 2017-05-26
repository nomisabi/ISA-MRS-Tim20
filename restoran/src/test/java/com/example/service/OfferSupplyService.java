package com.example.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Supply;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OfferSupplyService {
	
	@Autowired
	private OfferSupplyService osService;
	
/*
	@Before
	public void setUp() throws Exception {
		Supply s= new Supply( "123", "2017-05-24T23:30:00.000Z", "2017-05-30T23:30:00.000Z",false);
		osService.createSupply(s);
		
	}

	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testConcurrencyWriting() {

		Product productForUserOne = productService.findById(1L);
		Product productForUserTwo = productService.findById(1L);

		//modifikovanje istog objekta
		productForUserOne.setPrice(800L);
		productForUserTwo.setPrice(900L);

		//verzija oba objekta je 0
		assertEquals(0, productForUserOne.getVersion().intValue());
		assertEquals(0, productForUserTwo.getVersion().intValue());

		//pokusaj cuvanja prvog objekta
		productService.save(productForUserOne);

		//pokusaj cuvanja drugog objekta - Exception!
		productService.save(productForUserTwo);
	}*/
}
