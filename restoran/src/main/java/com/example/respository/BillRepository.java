package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.domain.RateWaiter;

@Repository
public interface BillRepository  extends JpaRepository<RateWaiter ,Long> {
	
	@Query(value="select e.first_name, e.last_name, sum(b.price) FROM (bill b inner join employee e on e.id=b.employee_id ) inner join "+
			"restaurant_employee re on re.employee_id=e.id where re.restaurant_id=?1 GROUP BY b.employee_id, e.first_name, e.last_name", nativeQuery=true)
	Collection<Object[]> getIncomesByWaiter(Long id);

	@Query(value="select  substring(date,1,10), count(id) FROM bill where restaurant_id=?1 GROUP BY substring(date,1,10)", nativeQuery=true)
	Collection<Object[]> getVisits(Long id);
	
	@Query(value="select  substring(date,1,10), sum(price) FROM bill where restaurant_id=?1 "+
			"  GROUP BY substring(date,1,10)", nativeQuery=true)
	Collection<Object[]> getIncomes(Long id);
}
