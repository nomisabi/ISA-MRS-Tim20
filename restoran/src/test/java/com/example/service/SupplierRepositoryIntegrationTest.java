package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Supplier;
import com.example.domain.System_manager;
import com.example.respository.SupllierRepository;
import com.example.respository.SystemManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierRepositoryIntegrationTest {

	@Autowired
	SupllierRepository repository;

	Supplier s;

	@Before
	public void setUp() {
		s = new Supplier("sup2","sup@sup.com", "sup1");
		s = repository.save(s);

	}

	@Test
	public void getSupplier() {
		Supplier sFind = repository.findOne(s.getId());
		assertNotNull(sFind);
	}

	@Test
	public void createSupplier() {
		Supplier s1 = new Supplier("new_sup@gmail.com", "myPass", "myName");
		Supplier savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteSupplier() {
		Supplier s2 = new Supplier("del_sup@gmail.com", "myPass", "myName");
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}

	@Test
	public void updateSupplier() {
		repository.updatePass(s.getId(), s.getEmail(), "pass", s.getName(), false);
		Supplier updateS = repository.findOne(s.getId());
		assertEquals("sup@sup.com", updateS.getEmail());
		assertEquals("pass", updateS.getPassword());
		assertEquals("sup2", updateS.getName());
		assertEquals(false, updateS.isActive());
	}
}
