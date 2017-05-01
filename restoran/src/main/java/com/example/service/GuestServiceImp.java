package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.domain.Guest;
import com.example.respository.GuestRepository;



@Service
public class GuestServiceImp implements GuestService {
	//private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GuestRepository guestRepository;
	
	@Override
	public Guest getGuest(Long id){
		return this.guestRepository.findOne(id);
	}
	
	@Override
	public Guest addGuest(Guest guest){
		Assert.notNull(guest, "Guest could not be null.");
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
		guestRepository.delete(id);
		return guestRepository.save(guest);
	}
}
