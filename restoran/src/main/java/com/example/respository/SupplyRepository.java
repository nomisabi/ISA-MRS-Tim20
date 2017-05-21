package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Manager;
import com.example.domain.Region;
import com.example.domain.Supply;

@Repository
public interface SupplyRepository extends CrudRepository<Supply, Long> {
	
	@Query(value="SELECT Object(s) FROM Supply s WHERE s.restaurant.id = ?1", nativeQuery=false)
	public Collection<Supply> getSupplyByRest(Long id);

	@Modifying
	@Transactional
    @Query("UPDATE Supply s SET s.chosed = ?2 WHERE s.id = ?1")
    int updateStatus(Long id, boolean choosed);
}
