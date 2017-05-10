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

	@Query("SELECT Object(g) FROM Guest g WHERE g.email = ?1 AND g.password = ?2")
	public Guest findByEmailAndPass(String email, String password);

	@Query("SELECT Object(g) FROM Guest g WHERE g.id != ?1 AND "
			+ "g.id NOT IN (SELECT f.guest.id FROM Friendship f WHERE f.idFriend = ?1) AND "
			+ "g.id NOT IN (SELECT f.idFriend FROM Friendship f WHERE f.guest.id = ?1) AND "
			+ "CONCAT(LOWER(g.firstName),' ',LOWER(g.lastName)) LIKE LOWER(CONCAT('%',?2,'%')))")
	public Collection<Guest> findByName(Long id, String firstName);

	@Query("SELECT Object(g) FROM Guest g JOIN g.friends f WHERE f.idFriend = ?1 AND f.requestAccepted = ?2")
	public Collection<Guest> getRequests(Long id, boolean requestAccepted);

	@Query("SELECT Object(g) FROM Guest g, Friendship f WHERE f.requestAccepted = ?2 AND ((f.idFriend = g.id AND f.guest.id = ?1) OR (f.guest.id = g.id AND f.idFriend = ?1))")
	public Collection<Guest> getFriends(Long id, boolean requestAccepted);

	@Query("SELECT Object(g) FROM Guest g, Friendship f WHERE f.idFriend = ?1 AND f.guest.id = g.id AND f.requestAccepted = false")
	public Collection<Guest> getRequests(Long id);

	@Query("SELECT Object(g) FROM Guest g WHERE g.id NOT IN (SELECT f.guest.id FROM Friendship f)")
	public Collection<Guest> getSearchGuests();

}
