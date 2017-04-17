package com.example.respository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;
import com.example.domain.Guest;

@Repository
public class GuestRepositoryImp implements GuestRepository {
	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, Guest> guests = new ConcurrentHashMap<Long, Guest>();

	@Override
	public Guest createGuest(Guest guest) {
		Long id = guest.getId();
		if (id == null) {
			id = counter.incrementAndGet();
			guest.setId(id);
		}
		this.guests.put(id, guest);
		return guest;
	}

	@Override
	public Collection<Guest> findAll() {
		return this.guests.values();
	}

	@Override
	public Guest findOne(Long id) {
		return this.guests.get(id);
	}

}
