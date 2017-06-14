
package com.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.GuestReservation;
import com.example.domain.Reservation;
@Repository
public interface GuestReservationRepository extends JpaRepository<GuestReservation, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE GuestReservation g SET g.accepted = true WHERE g.id = ?1")
	int confirmReservation(Long id);

	@Query("SELECT gr.id FROM GuestReservation gr WHERE gr.guest.id = ?2 AND gr.reservation.id = ?1")
	public Long getGuestOfReservation(Long idReservation, Long idGuest);
	
	@Query("SELECT gr.reservation FROM GuestReservation gr WHERE gr.id = ?1")
	public Reservation getReservationId(Long idGuestReservation);
	
	

}
