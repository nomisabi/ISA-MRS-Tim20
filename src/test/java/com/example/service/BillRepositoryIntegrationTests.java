package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Bill;
import com.example.domain.Supplier;
import com.example.respository.BillRepository;
import com.example.respository.SupllierRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BillRepositoryIntegrationTests {

	@Autowired
	BillRepository repository;

	Bill b;

	@Before
	public void setUp() {
		b = new Bill();
		b.setDate((new Date()).toString());
		b = repository.save(b);
	}

	@Test
	public void getBill() {
		Bill bFind = repository.findOne(b.getId());
		assertNotNull(bFind);
	}

	@Test
	public void createBill() {
		Bill s1 = new Bill();
		Bill savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteBill() {
		Bill s2 = new Bill();
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}
	
	@Test
	public void getIncome() {
		Collection<Object[]> incomoe = repository.getIncomes(1L);
		assertNotEquals(0, incomoe.size());	
	}
	
	@Test
	public void getIncomeWaiter() {
		Collection<Object[]> incomoe = repository.getIncomesByWaiter(1L);
		assertNotEquals(0, incomoe.size());	
	}
	
	@Test
	public void getVisits() {
		Collection<Object[]> incomoe = repository.getVisits(1L);
		assertNotEquals(0, incomoe.size());
	}


}
