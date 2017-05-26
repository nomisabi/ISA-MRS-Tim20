package com.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.RateMenuItem;

public interface RateMenuItemRepository extends JpaRepository<RateMenuItem, Long> {

}
