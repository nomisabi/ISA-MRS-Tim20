package com.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.RateRestaurant;
@Repository
public interface RateRestaurantRepository extends JpaRepository<RateRestaurant,Long> {

}
