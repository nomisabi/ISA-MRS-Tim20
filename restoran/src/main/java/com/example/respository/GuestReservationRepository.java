
package com.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.GuestReservation;

public interface GuestReservationRepository extends JpaRepository<GuestReservation, Long> {

}

