package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.DrinkMenuItemReservation;

public interface DrinkMenuItemReservationRepository extends JpaRepository<DrinkMenuItemReservation, Long> {
	@Query("SELECT Object(mr) FROM DrinkMenuItemReservation mr WHERE "
			+ " mr.reservation.id = ?1 AND mr.guest.id = ?2")
	public Collection<DrinkMenuItemReservation> getMenuItems(Long idReservation, Long idGuest);
	
	@Modifying
	@Transactional
	@Query("UPDATE DrinkMenuItemReservation m SET m.quantity = ?2 WHERE m.id = ?1")
	int update(Long id, int  quantity);
	
	@Query(value="SELECT count(dm.id) FROM Drink_Menu_Item_Reservation AS dm  WHERE dm.drink_Menu_Item_id = ?1 ", nativeQuery=true)
	public int getMenuItemsByMenuitem(Long idMenuItem);

}
