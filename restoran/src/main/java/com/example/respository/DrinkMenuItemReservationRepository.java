package com.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.DrinkMenuItemReservation;

public interface DrinkMenuItemReservationRepository extends JpaRepository<DrinkMenuItemReservation, Long> {

}
