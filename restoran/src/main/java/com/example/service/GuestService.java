package com.example.service;

import java.util.Collection;

import com.example.domain.Guest;

public interface GuestService {

	Guest createGuest(Guest guest) throws Exception;

	Collection<Guest> findAll();

	Guest findOne(Long id);

}
