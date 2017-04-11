package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SystemManagerTest {
	System_manager sm;
	
	@Before
	public void setUp(){
		sm = new System_manager("proba@proba.com","proba");
	}
	
	@Test
	public void testSysManEmail(){
		assertEquals("proba@proba.com", sm.getEmail());
	}
	
	@Test
	public void testSysManPassword(){
		assertEquals("proba", sm.getPassword());
	}
	

	@Test
	public void testSysManNotNull(){
		assertNotNull(sm);
	}

}
