package com.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.domain.RateRestaurant;
@Repository
public interface RateRestaurantRepository extends JpaRepository<RateRestaurant,Long> {
	
	@Query("SELECT r.rate FROM RateRestaurant r WHERE r.reservation.id = ?1 AND r.guest.id = ?2")
	public int getRate(Long idReservation, Long idGuest);

}
