package com.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.domain.Guest;
import com.example.domain.RegistrationToken;
@Repository
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {
	
	@Query("SELECT t.guest FROM RegistrationToken t WHERE token = ?1")
	public Guest getGuest(String token);
	
	@Query("SELECT id FROM RegistrationToken t WHERE token = ?1")
	public Long getId(String token);

}
