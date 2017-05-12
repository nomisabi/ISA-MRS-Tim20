
package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("SELECT Object(r) FROM Reservation r WHERE r.restaurant.id = ?1")
	public Collection<Reservation> getAllReservationOfRestaurant(Long id);

	@Query("SELECT Object(r) FROM Reservation r WHERE r.restaurant.id = ?1 AND "
			+ "((r.startTime>=?2 AND r.startTime < ?3) OR (r.endTime > ?2 AND r.endTime <= ?3))")
	public Collection<Reservation> getAllReservationOfRestaurantInTime(Long id, String dateStart, String dateEnd);

	@Query("SELECT Object(r) FROM Reservation r WHERE r.restaurant.id = ?1 AND r.table.id = ?2 AND "
			+ "((r.startTime>=?3 AND r.startTime < ?4) OR (r.endTime > ?3 AND r.endTime <= ?4)) ")
	public Collection<Reservation> get(Long idRestaurant, Long idTable, String dateStart, String dateEnd);
	
	@Query("SELECT Object(r) FROM Reservation r, GuestReservation g WHERE r.id = g.reservation.id AND g.guest.id = ?1")
	public Collection<Reservation> getVisitedRestaurant(Long id);

}
