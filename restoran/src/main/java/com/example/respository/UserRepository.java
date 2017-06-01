package com.example.respository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	@Modifying
	@Transactional
    @Query("UPDATE User u SET u.email = ?2 WHERE u.id = ?1")
    int updateGuest(Long id, String email);
	
	@Modifying
	@Transactional
    @Query("UPDATE User u SET u.password = ?2 WHERE u.id = ?1")
    int setPassword(Long id, String password);

}
