package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class FriendshipTest {

	Guest guest;
	Friendship friendship;

	@Before
	public void setUp() {
		guest = new Guest("proba@proba.com", "proba", "Proba", "ProbaLastName");
		friendship = new Friendship(guest, 1L, false);
	}

	@Test
	public void testGetGuest() {
		assertEquals(guest, friendship.getGuest());
	}

	@Test
	public void testIsAccepted() {
		assertEquals(false, friendship.isRequestAccepted());
	}

	@Test
	public void testSetGuest() {
		Guest g = new Guest();
		friendship.setGuest(g);
		assertEquals(g, friendship.getGuest());
	}

	@Test
	public void testSetAccepted() {
		friendship.setRequestAccepted(true);
		assertEquals(true, friendship.isRequestAccepted());
	}

	@Test
	public void testGuestNotNull() {
		assertNotNull(friendship);
	}

}
