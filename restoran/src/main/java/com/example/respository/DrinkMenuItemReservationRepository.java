package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.DrinkMenuItemReservation;

public interface DrinkMenuItemReservationRepository extends JpaRepository<DrinkMenuItemReservation, Long> {
	@Query("SELECT Object(mr) FROM DrinkMenuItemReservation mr WHERE "
			+ " mr.reservation.id = ?1 AND mr.guest.id = ?2")
	public Collection<DrinkMenuItemReservation> getMenuItems(Long idReservation, Long idGuest);

}
