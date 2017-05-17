package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

	@Query(value="SELECT Object(re) FROM Region re WHERE re.restaurant.id = ?1", nativeQuery=false)
	public Collection<Region> getRequests(Long id);

}
