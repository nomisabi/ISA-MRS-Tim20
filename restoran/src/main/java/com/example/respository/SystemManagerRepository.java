package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;

public interface SystemManagerRepository  extends CrudRepository<System_manager, Long>  {


	@Modifying
	@Transactional
    @Query("UPDATE System_manager m SET m.password = ?3, m.email=?2, m.firstName=?4, m.lastName=?5 WHERE m.id = ?1")
    int updatePass(Long id, String email, String password, String firstName, String lastName);
}
