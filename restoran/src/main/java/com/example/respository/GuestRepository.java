package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
	
	@Modifying
	@Transactional
    @Query("UPDATE Guest g SET g.email = ?2, g.firstName = ?3, g.lastName = ?4, g.address = ?5 WHERE g.id = ?1")
    int updateGuest(Long id, String email, String firstName, String lastName, String address);
	
	
	@Query("SELECT Object(g) from Guest g WHERE g.email = ?1 and g.password=?2")
	public Guest findByEmailAndPass(String email, String password);
	
	@Query("SELECT Object(g) from Guest g WHERE g.firstName like CONCAT('%',?1,'%') or g.lastName like CONCAT('%',?1,'%')")
	public Collection<Guest> findByName(String firstName);

}
