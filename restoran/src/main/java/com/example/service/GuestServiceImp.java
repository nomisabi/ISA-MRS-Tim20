package com.example.service;

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
	public void createGuest(Guest guest) throws Exception {
		logger.info("> create");
		guestRepository.createGuest(guest);
		logger.info("< create");

	}

	@Override
	public boolean signUp(Guest guest) throws Exception {
		if (!guest.getPassword().equals(guest.getPassword2())) {
			logger.info("password not equals");
			return false;
		}

		String mail = guest.getEmail();
		if (!mail.contains("@")) {
			logger.info("invalid email");
			return false;
		}
		if (!mail.contains(".")) {
			logger.info("invalid email");
			return false;
		}

		createGuest(guest);

		return true;

	}

}
