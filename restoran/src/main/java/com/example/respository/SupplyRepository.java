package com.example.respository;

import java.util.Collection;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Manager;
import com.example.domain.Region;
import com.example.domain.Supply;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {
	
	@Query(value="SELECT Object(s) FROM Supply s WHERE s.restaurant.id = ?1 AND s.chosed  = false", nativeQuery=false)
	public Collection<Supply> getSupplyByRest(Long id);
	
	@Query(value="SELECT Object(s) FROM Supply s WHERE s.restaurant.id = ?1 AND s.chosed  = true", nativeQuery=false)
	public Collection<Supply> getSupplyByRestChoosed(Long id);

	@Query(value="SELECT s.id, s.chosed, s.from_date, s.name, s.to_date, s.rest_id, s.version FROM Supply s INNER JOIN Offer o ON s.id=o.supply_id WHERE o.status=2 AND o.supplier_id=?1", nativeQuery=true)
	public Collection<Supply> getWatingSupply(Long id);
	
	@Query(value="SELECT s.id, s.chosed, s.from_date, s.name, s.to_date, s.rest_id, s.version FROM Supply s INNER JOIN Offer o ON s.id=o.supply_id WHERE o.status=1 AND o.supplier_id=?1", nativeQuery=true)
	public Collection<Supply> getNotChosedSupply(Long id);
	
	@Modifying
	@Transactional
    @Query(value="UPDATE Supply  SET chosed = ?2 WHERE id = ?1", nativeQuery=true)
    int updateStatus(Long id, boolean choosed);
	
	@Query(value="SELECT s.id, s.chosed, s.from_date, s.name, s.to_date, s.rest_id, s.version FROM Supply s INNER JOIN Offer o ON o.supply_id=s.id  WHERE  o.supplier_id= ?1 ", nativeQuery=true)
	public Collection<Supply> getSupplyWithMyOffer(Long id);

}
