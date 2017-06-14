
package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Guest;
import com.example.domain.Reservation;
import com.example.domain.Restaurant;
import com.example.domain.TableReservation;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("SELECT Object(t) FROM Reservation r, TableReservation t WHERE "
			+ " t.reservation.id = r.id AND r.restaurant.id = ?1 AND "
			+ "((r.startTime>=?2 AND r.startTime < ?3) OR (r.endTime > ?2 AND r.endTime <= ?3))")
	public Collection<TableReservation> getAllReservationTable(Long idRestaurant, String dateStart, String dateEnd);

	@Query("SELECT Object(r) FROM Reservation r, GuestReservation g WHERE r.id = g.reservation.id AND g.guest.id = ?1")
	public Collection<Reservation> getVisitedRestaurant(Long idGuest);

	@Query("SELECT Object(r) FROM Reservation r, GuestReservation g WHERE r.id = g.reservation.id AND g.id = ?1")
	public Reservation getReservationOfGuest(Long id);

	@Query("SELECT Object(g) FROM Reservation r, GuestReservation gr, Guest g WHERE r.id = gr.reservation.id AND "
			+ " g.id = gr.guest.id AND g.id != ?2 AND r.id = ?1")
	public Collection<Guest> getGuestOfReservation(Long id, Long idGuest);

	@Query("SELECT Object(g) FROM GuestReservation gr, Guest g WHERE g.id = gr.guest.id" + " AND gr.id = ?1")
	public Guest getGuestOfGuestReservation(Long idGuestReservation);
	
	@Query("SELECT r.restaurant FROM Reservation r WHERE r.id = ?1")
	public Restaurant getRestaurant(Long idReservation);
	
	@Modifying
	@Transactional
	@Query("UPDATE Reservation r SET r.rate = true WHERE r.id = ?1")
	int setRate(Long id);
	
	

}
