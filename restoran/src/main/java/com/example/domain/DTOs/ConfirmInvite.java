
package com.example.domain.DTOs;

import java.util.Collection;

import com.example.domain.Guest;
import com.example.domain.Reservation;

public class ConfirmInvite {
	private Long id;
	private String token;
	private Guest guest;
	private Reservation reservation;
	private Collection<Guest> friends;

	public ConfirmInvite() {
	}

	public ConfirmInvite(Long id, Guest guest, Reservation reservation, Collection<Guest> friends) {
		super();
		this.id = id;
		this.guest = guest;
		this.reservation = reservation;
		this.friends = friends;
	}

	public ConfirmInvite(Reservation reservation) {
		super();
		this.reservation = reservation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
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

	@Override
	public String toString() {
		return "ConfirmInvite [id=" + id + ", guest=" + guest + ", reservation=" + reservation + ", friends=" + friends
				+ "]";
	}

}

