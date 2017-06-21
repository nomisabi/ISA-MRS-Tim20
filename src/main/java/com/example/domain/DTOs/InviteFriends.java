
package com.example.domain.DTOs;

import java.util.Collection;

import com.example.domain.Guest;
import com.example.domain.Reservation;

public class InviteFriends {
	private Reservation reservation;
	private Collection<Guest> friends;
	private Guest guest;

	public InviteFriends() {

	}

	public InviteFriends(Reservation reservation, Collection<Guest> friends) {
		super();
		this.reservation = reservation;
		this.friends = friends;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Collection<Guest> getFriends() {
		return friends;
	}

	public void setFriends(Collection<Guest> friends) {
		this.friends = friends;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	@Override
	public String toString() {
		return "InviteFriends [reservation=" + reservation + ", friends=" + friends + "]";
	}

}
