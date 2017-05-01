package com.example.respository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Guest;

public interface GuestRepository extends CrudRepository<Guest, Long> {
	

}
