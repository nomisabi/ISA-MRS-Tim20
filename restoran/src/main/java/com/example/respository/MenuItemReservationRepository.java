package com.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.MenuItemReservation;

public interface MenuItemReservationRepository extends JpaRepository<MenuItemReservation, Long> {

}
