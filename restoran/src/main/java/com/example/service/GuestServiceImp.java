package com.example.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Guest;
import com.example.respository.GuestRepositoryImp;


@Service
public class GuestServiceImp implements GuestService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GuestRepositoryImp guestRepository;

	@Override
	public Guest createGuest(Guest guest) throws Exception {
		logger.info("> create");
		if (guest.getId() != null) {
			logger.error("Pokusaj kreiranja novog entiteta, ali Id nije null.");
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		Guest savedGuest = guestRepository.createGuest(guest);
		logger.info("< create");
		return savedGuest;

	}

	@Override
	public Collection<Guest> findAll() {
		logger.info("> findAll");
		Collection<Guest> guests = guestRepository.findAll();
		logger.info("< findAll");
		return guests;
	}

	@Override
	public Guest findOne(Long id) {
		logger.info("> findOne id:{}", id);
		Guest guest = guestRepository.findOne(id);
		logger.info("< findOne id:{}", id);
		return guest;
	}
	
	@Override
	public Guest findByEmail(String email){
		logger.info("> findByEmail email:{}", email);
		Guest guest = guestRepository.findByEmail(email);
		logger.info("< findByEmail email:{}", email);
		return guest;
		
	}

	@Override
	public boolean isGuestExist(Guest guest){
		return guestRepository.isGuestExist(guest);
		
	}
	
	@Override
	public Guest update(Guest guest) throws Exception{
		logger.info("> update id:{}", guest.getId());
        Guest guestToUpdate = findOne(guest.getId());
        if (guestToUpdate == null) {
            logger.error(
                    "Pokusaj azuriranja gosta, ali je on nepostojeci.");
            throw new Exception("Trazeni gost nije pronadjen.");
        }
        guestToUpdate.setFirstName(guest.getFirstName());
        guestToUpdate.setLastName(guest.getLastName());
        Guest updatedGuest = guestRepository.createGuest(guestToUpdate);
        logger.info("< update id:{}", guest.getId());
        return updatedGuest;
	}

}
