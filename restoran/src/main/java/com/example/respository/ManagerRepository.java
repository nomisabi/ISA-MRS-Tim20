package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.User;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long>{

	@Modifying
	@Transactional
    @Query("UPDATE Manager m SET m.password = ?2, m.email=?3, m.firstName=?4, m.lastName=?5, m.active=?6 WHERE m.id = ?1")
    int updatePass(Long id, String password, String email, String firstName, String lastName, boolean active);

    @Query(value="SELECT r.id FROM restaurant r INNER JOIN restaurant_manager rm ON r.id=rm.restaurant_id WHERE rm.manager_id = ?1", nativeQuery=true)
	Long getRest(Long id);
	
	/*void createManager(Manager m);
	
	Employee createEmployee(Employee e);
	
	Supplier createSupplier(Supplier s);
	
	Collection<Manager> findAll();

	Manager findOne(Long id);

	Manager findByEmail(String email);

	boolean isManagerExist(Manager man);
	
	boolean changePassword(String newP, String oldP, Manager m);
	
	void setLogedIn(Manager m);
	
	Manager getLogedIn();*/
}
