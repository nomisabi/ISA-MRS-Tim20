
package com.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.GuestReservation;

public interface GuestReservationRepository extends JpaRepository<GuestReservation, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE GuestReservation g SET g.accepted = true WHERE g.id = ?1")
	int update(Long id);

}

