package com.example.respository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Guest;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
	
	@Modifying
	@Transactional
    @Query("UPDATE Guest g SET g.email = ?2, g.firstName = ?3, g.lastName = ?4, g.address = ?5 WHERE g.id = ?1")
    int updateGuest(Long id, String email, String firstName, String lastName, String address);

}
