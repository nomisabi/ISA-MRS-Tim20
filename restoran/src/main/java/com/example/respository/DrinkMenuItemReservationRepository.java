package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.DrinkMenuItem;
import com.example.domain.DrinkMenuItemReservation;

public interface DrinkMenuItemReservationRepository extends JpaRepository<DrinkMenuItemReservation, Long> {
	@Query("SELECT Object(m) FROM DrinkMenuItemReservation mr, DrinkMenuItem m WHERE mr.drinkMenuItem.id = m.id AND "
			+ " mr.reservation.id = ?1 AND mr.guest.id = ?2")
	public Collection<DrinkMenuItem> getMenuItems(Long idReservation, Long idGuest);

}
