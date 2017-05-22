package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.MenuItemReservation;

public interface MenuItemReservationRepository extends JpaRepository<MenuItemReservation, Long> {
	@Query("SELECT Object(mr) FROM MenuItemReservation mr WHERE "+
          " mr.reservation.id = ?1 AND mr.guest.id = ?2")
	public Collection<MenuItemReservation> getMenuItems(Long idReservation, Long idGuest);

}
