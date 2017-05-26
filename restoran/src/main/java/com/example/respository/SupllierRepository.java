package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.System_manager;

@Repository
public interface SupllierRepository extends CrudRepository<Supplier, Long>{

	@Modifying
	@Transactional
    @Query("UPDATE Supplier s SET s.password = ?3, s.email=?2, s.name=?4, s.active=?5 WHERE s.id = ?1")
    int updatePass(Long id, String email, String password, String name, boolean active);
	
    @Query(value="select s.id, s.email, s.password, s.name, s.active from supplier s inner join restaurant_suppliers rs on rs.suppliers_id=s.id where rs.restaurant_id!=?1 " +
     " and s NOT IN (select s from supplier s inner join restaurant_suppliers rs on rs.suppliers_id=s.id where rs.Restaurant_id=?1)", nativeQuery=true)
    Collection<Supplier> getSuppByRest(Long id_r);


}
