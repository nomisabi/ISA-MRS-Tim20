package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Friendship;
import com.example.domain.Guest;
import com.example.respository.FriendshipRepository;
import com.example.respository.GuestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestRepositoryIntegrationTests {

	@Autowired
	GuestRepository repository;
	@Autowired
	FriendshipRepository friendshipRepository;

	Guest guest;
	Guest friend;
	Friendship friendship;

	@Before
	public void setUp() {
		guest = new Guest("novi@gmail.com", "123456", "proba", "proba1");
		guest = repository.save(guest);
		friend = new Guest("friend@gmail.com", "123456", "friend", "friend");
		friend = repository.save(friend);
		friendship = new Friendship(guest, friend.getId(), false);
		friendship = friendshipRepository.save(friendship);
	}

	@Test
	public void getGuest() {
		Guest guestFind = repository.findOne(guest.getId());
		assertNotNull(guestFind);
	}

	@Test
	public void createGuest() {
		Guest guest = new Guest("novi@gmail.com", "145564");
		Guest savedGuest = repository.save(guest);
		assertEquals(guest, savedGuest);
	}

	@Test
	public void findByName() {
		Collection<Guest> guests = repository.findByName(2L, "proba");
		assertNotEquals(0, guests.size());
	}

	@Test
	public void updateGuest() {
		repository.updateGuest(1L, "novi@gmail.com", "Nena", "Djeric", "address");
		Guest updateGuest = repository.findOne(1L);
		assertEquals("novi@gmail.com", updateGuest.getEmail());
		assertEquals("Nena", updateGuest.getFirstName());
		assertEquals("Djeric", updateGuest.getLastName());
		assertEquals("address", updateGuest.getAddress());
	}

	@Test
	public void saveFriendship() {
		Friendship savedFriendship = friendshipRepository.findOne(friendship.getId());
		assertEquals(friendship.getId(), savedFriendship.getId());
		assertEquals(friendship.getIdFriend(), savedFriendship.getIdFriend());
		assertEquals(friendship.getGuest().getId(), savedFriendship.getGuest().getId());
	}

	@Test
	public void getRequests() {
		Collection<Guest> guests = repository.getRequests(friend.getId());
		assertEquals(1, guests.size());
		for (Guest guest2 : guests) {
			assertEquals(guest.getId(), guest2.getId());
		}
	}

	@Test
	public void deleteRequest() {
		friendshipRepository.delete(friendship.getId());
		Friendship f = friendshipRepository.findOne(friendship.getId());
		assertNull(f);
	}

	@Test
	public void getFriends() {
		friendshipRepository.confirmFriendship(guest.getId(), friend.getId());
		Collection<Guest> guests = repository.getFriends(friend.getId());
		assertEquals(1, guests.size());
		for (Guest guest2 : guests) {
			assertEquals(guest.getId(), guest2.getId());
		}

		Collection<Guest> friends = repository.getFriends(guest.getId());
		assertEquals(1, friends.size());
		for (Guest frend2 : friends) {
			assertEquals(friend.getId(), frend2.getId());
		}

	}

	@Test
	public void searchFriends() {
		friendshipRepository.confirmFriendship(guest.getId(), friend.getId());
		Collection<Guest> guests = repository.searchFriends(guest.getId(), friend.getFirstName());
		assertEquals(1, guests.size());
		for (Guest guest2 : guests) {
			assertEquals(friend.getId(), guest2.getId());
		}

	}
}
