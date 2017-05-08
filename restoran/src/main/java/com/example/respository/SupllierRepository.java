package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Supplier;
import com.example.domain.System_manager;

@Repository
public interface SupllierRepository extends CrudRepository<Supplier, Long>{

	@Modifying
	@Transactional
    @Query("UPDATE Supplier s SET s.password = ?2, s.email=?3, s.name=?4, s.active=?5 WHERE s.id = ?1")
    int updatePass(Long id, String password, String email, String name, boolean active);

	//@Query(value="SELECT r.id FROM restaurant r INNER JOIN restaurant_manager rm ON r.id=rm.restaurant_id WHERE rm.manager_id = ?1", nativeQuery=true)
	//Long getRest(Long id);
}
