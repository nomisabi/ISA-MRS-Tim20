package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.domain.Friendship;
import com.example.domain.Guest;
import com.example.domain.TypeOfUser;
import com.example.domain.User;
import com.example.respository.FriendshipRepository;
import com.example.respository.GuestRepository;
import com.example.respository.UserRepository;

@Service
public class GuestServiceImp implements GuestService {

	@Autowired
	private GuestRepository guestRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FriendshipRepository friendshipRepository;

	@Override
	public Guest getGuest(Long id) {
		return guestRepository.findOne(id);
	}

	@Override
	public Guest addGuest(Guest guest) {
		Assert.notNull(guest, "Guest could not be null.");
		User u = new User(guest.getEmail(), guest.getPassword(), TypeOfUser.GUEST);
		userRepository.save(u);
		return guestRepository.save(guest);
	}

	@Override
	public Collection<Guest> getAllGuests() {
		return (Collection<Guest>) guestRepository.findAll();
	}

	@Override
	public boolean isExists(Long id) {
		return guestRepository.exists(id);
	}

	@Override
	public Guest updateGuest(Long id, Guest guest) {
		Assert.notNull(guest.getEmail(), "Email could not be null.");
		Assert.notNull(guest.getPassword(), "Password could not be null.");
		int id1 = guestRepository.updateGuest(id, guest.getEmail(), guest.getFirstName(), guest.getLastName(),
				guest.getAddress());
		System.out.println(id1);
		return guestRepository.findOne(id);

	}

	@Override
	public void sendFriendRequest(Guest guest, Guest friend) {
		Friendship friendship = new Friendship(guest, friend.getId(), false);
		friendshipRepository.save(friendship);
	}

	@Override
	public void addFriend(Long id, Guest guest, Guest friend) {
		sendFriendRequest(guest, friend);
		friendshipRepository.updateFriendship(id, true);

	}

	@Override
	public Guest findByEmailAndPass(String email, String password) {
		return guestRepository.findByEmailAndPass(email, password);
	}

	@Override
	public Collection<Guest> searchGuest(Long id, String search) {
		return guestRepository.findByName(id, search);
	}

	@Override
	public Collection<Guest> findFriends(Long id) {
		return guestRepository.getFriends(id);
	}
}
