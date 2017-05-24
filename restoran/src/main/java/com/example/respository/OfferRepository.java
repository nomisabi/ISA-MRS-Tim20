package com.example.respository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Manager;
import com.example.domain.Offer;
import com.example.domain.Offer_status;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long>{
	
	@Modifying
	@Transactional
    @Query(value="UPDATE offer SET supply_id=?2 WHERE id=?1", nativeQuery=true)
    int updateName(Long id_off, Long id_s);
	
	@Modifying
	@Transactional
    @Query(value="UPDATE Offer SET status = ?2 WHERE id = ?1",  nativeQuery=true)
    int updateStatus(Long id, int status);
	
	@Modifying
	@Transactional
    @Query(value="UPDATE Offer SET quality = ?2, price=?3 WHERE id = ?1",  nativeQuery=true)
    int update(Long id, int quality, double price);
}
