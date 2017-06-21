package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.example.domain.Friendship;
import com.example.domain.Guest;
import com.example.domain.TypeOfUser;
import com.example.domain.User;
import com.example.respository.FriendshipRepository;
import com.example.respository.GuestRepository;
import com.example.respository.RegistrationTokenRepository;
import com.example.respository.UserRepository;

@Service
@Transactional(readOnly = true)
public class GuestServiceImp implements GuestService {

	@Autowired
	private GuestRepository guestRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FriendshipRepository friendshipRepository;
	@Autowired
	private RegistrationTokenRepository registrationRepository;

	@Override
	public Guest getGuest(Long id) {
		return guestRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = false)
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
	@Transactional(readOnly = false)
	public Guest updateGuest(Long id, Guest guest) {
		Assert.notNull(guest.getEmail(), "Email could not be null.");
		Assert.notNull(guest.getPassword(), "Password could not be null.");
		int id1 = guestRepository.updateGuest(id, guest.getEmail(), guest.getFirstName(), guest.getLastName(),
				guest.getAddress());
		System.out.println(id1);
		guestRepository.flush();
		return guestRepository.findOne(id);

	}

	@Override
	@Transactional(readOnly = false)
	public void sendFriendRequest(Guest guest, Guest friend) {
		Friendship friendship = new Friendship(guest, friend.getId(), false);
		friendshipRepository.save(friendship);
	}

	@Override
	@Transactional(readOnly = false)
	public void addFriend(Long guestId, Long friendId) {
		friendshipRepository.confirmFriendship(friendId, guestId);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteFriend(Long guestId, Long friendId) {
		Friendship f = friendshipRepository.findByGuestAndFriendId(guestId, friendId);
		if (f == null) {
			return false;
		}
		friendshipRepository.delete(f.getId());
		return true;

	}

	@Override
	public Guest findByEmailAndPass(String email, String password) {
		return guestRepository.findByEmailAndPass(email, password);
	}
	
	@Override
	public Guest findByEmail(String email) {
		return guestRepository.findByEmail(email);
	}

	@Override
	public Collection<Guest> searchGuest(Long id, String search) {
		return guestRepository.findByName(id, search);
	}

	@Override
	public Collection<Guest> findFriends(Long id) {
		return guestRepository.getFriends(id);
	}

	@Override
	public Collection<Guest> getRequests(Long id) {
		return guestRepository.getRequests(id);
	}

	@Override
	public Collection<Guest> searchFriends(Long id, String name) {
		return guestRepository.searchFriends(id, name);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void setPassword(Long id, String password){
		guestRepository.setPassword(id, password);
	}
	
	@Override
	public Guest getGuestId(String token){
		return registrationRepository.getGuest(token);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void setRegistrationAccept(Long id){
		guestRepository.setRegistrationAccept(id);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void deleteToken(String token){
		Long id = registrationRepository.getId(token);
		registrationRepository.delete(id);
	}
}
