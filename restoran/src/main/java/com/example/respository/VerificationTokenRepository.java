package com.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	@Query("SELECT t.guestReservation.id FROM VerificationToken t WHERE t.token = ?1")
	public Long getGuestReservationId(String token);
	
	@Query("SELECT id FROM VerificationToken t WHERE guestReservation.id = ?1")
	public Long getId(Long id);
}
