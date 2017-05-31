package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.domain.RateMenuItem;
import com.example.domain.RateWaiter;

@Repository
public interface RateWaiterRepository extends JpaRepository<RateWaiter ,Long> {
	@Query(value="Select e.first_name, e.last_name, avg(rw.rate) from rate_waiter  rw inner join employee e on rw.employee_id=e.id "+
			"where rw.restaurant_id=?1 group by e.id", nativeQuery=true)
	Collection<Object[]> getAvgByWaiter(Long id);

}
