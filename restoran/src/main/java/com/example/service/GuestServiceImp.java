package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.domain.Guest;
import com.example.domain.TypeOfUser;
import com.example.domain.User;
import com.example.respository.GuestRepository;
import com.example.respository.UserRepository;



@Service
public class GuestServiceImp implements GuestService {
	//private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GuestRepository guestRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Guest getGuest(Long id){
		return this.guestRepository.findOne(id);
	}
	
	@Override
	public Guest addGuest(Guest guest){
		Assert.notNull(guest, "Guest could not be null.");
		User u= new User(guest.getEmail(), guest.getPassword(), TypeOfUser.GUEST);
		userRepository.save(u);
		return guestRepository.save(guest);
	}
	
	@Override
	public Collection<Guest> getAllGuests(){
		return (Collection<Guest>) guestRepository.findAll();
	}
	
	@Override
	public boolean isExists(Long id){
		return guestRepository.exists(id);
	}
	
	@Override
	public Guest updateGuest(Long id,Guest guest){     //zavrsiti
		Assert.notNull(guest.getEmail(), "Email could not be null.");
		Assert.notNull(guest.getPassword(),"Password could not be null." );
		int id1 = guestRepository.updateGuest(id, guest.getEmail(), guest.getFirstName(), guest.getLastName(),guest.getAddress());
		System.out.println(id1);
		return guestRepository.findOne(id);
		
		
	}
	
	@Override
	public Guest sendFriendRequest(Guest guest, Guest friend){
		//add guest to friend list requests
		return guest;
	}
	
	@Override
	public Guest addFriend(Guest guest, Guest friend){
		//add friend to guest list of friends
		//add guest to friend list of friends
		//delete friend from guest list requests
		return guest;
	}
	
	@Override
	public Guest deleteFriend(Guest guest, Guest friend){
		//delete friend from guest list friends
		//delete guest from friend list friends
		return guest;
	}
}
