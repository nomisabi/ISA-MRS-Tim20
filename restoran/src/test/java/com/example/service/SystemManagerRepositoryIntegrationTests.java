package com.example.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Guest;
import com.example.domain.System_manager;
import com.example.respository.GuestRepository;
import com.example.respository.SystemManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemManagerRepositoryIntegrationTests {

	@Autowired
	SystemManagerRepository repository;

	System_manager sm;

	@Before
	public void setUp() {
		sm = new System_manager("admin@admin.com", "admin", "admin", "admin");
		sm = repository.save(sm);

	}

	@Test
	public void getSysman() {
		System_manager smFind = repository.findOne(sm.getId());
		assertNotNull(smFind);
	}

	@Test
	public void createSysman() {
		System_manager sm = new System_manager("admin@admin.com", "admin", "admin", "admin");
		System_manager savedSM = repository.save(sm);
		assertEquals(sm, savedSM);
	}

	@Test
	public void deleteSysman() {
		System_manager sm = new System_manager("admin@admin.com", "admin", "admin", "admin");
		repository.save(sm);
		repository.delete(sm.getId());
		assertNull(repository.findOne(sm.getId()));
	}

	@Test
	public void updateSysman() {
		System_manager sm = new System_manager("admin@admin.com", "admin", "admin", "admin");
		repository.save(sm);
		repository.updatePass(sm.getId(), sm.getEmail(), "pass", sm.getFirstName(), "lastName");
		System_manager updateSM = repository.findOne(sm.getId());
		assertEquals("admin@admin.com", updateSM.getEmail());
		assertEquals("admin", updateSM.getFirstName());
		assertEquals("lastName", updateSM.getLastName());
		assertEquals("pass", updateSM.getPassword());
	}
}
