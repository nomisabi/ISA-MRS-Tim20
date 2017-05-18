
package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Reservation;
import com.example.domain.TableOfRestaurant;
import com.example.domain.TableReservation;

public interface TableReservationRepository extends JpaRepository<TableReservation, Long> {
	@Query("SELECT Object(t) FROM TableReservation t WHERE t.table.id = ?1 AND "
			+ "((t.startTime>=?2 AND t.startTime < ?3) OR (t.endTime > ?2 AND t.endTime <= ?3)) ")
	public Collection<TableReservation> get(Long idTable, String dateStart, String dateEnd);

	@Modifying
	@Transactional
	@Query("UPDATE TableReservation t SET t.reservation = ?2 WHERE t.id = ?1")
	int setReservation(Long id, Reservation reservation);

	@Query("SELECT Object(t) FROM TableReservation tr, TableOfRestaurant t WHERE tr.reservation.id = ?1 AND tr.table.id = t.id ")
	public Collection<TableOfRestaurant> getAllTableReservation(Long idReservation);

	@Query(value="SELECT Object(t) FROM TableReservation t WHERE t.table.id = ?1", nativeQuery=false)
	 public Collection<TableReservation> getByTable(Long idTable);
}

