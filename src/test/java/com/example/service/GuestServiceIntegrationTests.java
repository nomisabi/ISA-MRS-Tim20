package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Friendship;
import com.example.domain.Guest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestServiceIntegrationTests {
	
	@Autowired
	GuestService service;

	Guest guest;
	Guest friend;
	Friendship friendship;

	@Before
	public void setUp() {
		guest = new Guest("novi@gmail.com", "123456", "proba", "proba1");
		guest = service.addGuest(guest);
		friend = new Guest("friend@gmail.com", "123456", "friend", "friend");
		friend = service.addGuest(guest);
	}
	
	@Test
	public void getGuest() {
		Guest guestFind = service.getGuest(guest.getId());
		assertNotNull(guestFind);
	}
	
	@Test
	public void isExists() {
		boolean exist = service.isExists(guest.getId());
		assertEquals(true, exist);
	}
	
	@Test
	public void updateGuest() {
		Guest newGuest = new Guest("novi2@gmail.com", "12345612", "proba2", "proba2");
		newGuest = service.updateGuest(guest.getId(), newGuest);
		
		Guest findGuest = service.getGuest(guest.getId());
		
		assertEquals(findGuest.getFirstName(), newGuest.getFirstName());
		assertEquals(findGuest.getEmail(), newGuest.getEmail());
		assertEquals(findGuest.getLastName(), newGuest.getLastName());
		assertEquals(findGuest.getPassword(), newGuest.getPassword());
	}
	
	@Test
	public void sendRequest() {
		service.sendFriendRequest(guest, friend);
		Collection<Guest> request = service.getRequests(friend.getId());
		assertEquals(1, request.size());
		
	}
	
	@Test
	public void addFriend() {
		Collection<Guest> friends = service.findFriends(friend.getId());
		assertEquals(0, friends.size());
		service.sendFriendRequest(guest, friend);
		service.addFriend(friend.getId(), guest.getId());
		friends = service.findFriends(friend.getId());
		assertEquals(1, friends.size());
		
	}
	
	@Test
	public void setPass() {
		service.setPassword(guest.getId(), "novalozinka");
		Guest g = service.getGuest(guest.getId());
		assertEquals("novalozinka", g.getPassword());
	}
	
	@Test
	public void setRegistrationAccept() {
		assertEquals(false, guest.isAccepted());
		service.setRegistrationAccept(guest.getId());
		Guest g = service.getGuest(guest.getId());
		assertEquals(true, g.isAccepted());
	}

}
