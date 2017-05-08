package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Region;




public interface RegionRepository extends JpaRepository<Region, Long> {

	@Query("SELECT Object(t) FROM Region re WHERE re.restaurant.id = ?1")
	public Collection<Region> getRequests(Long id);

}
