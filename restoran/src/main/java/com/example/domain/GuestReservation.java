
package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GuestReservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "guest_id", nullable = false)
	private Guest guest;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reservation_id", nullable = false)
	private Reservation reservation;
	@Column
	private boolean accepted;

	public GuestReservation() {

	}

	public GuestReservation(Guest guest, Reservation reservation) {
		super();
		this.guest = guest;
		this.reservation = reservation;
	}

	public GuestReservation(Guest guest, Reservation reservation, boolean accepted) {
		super();
		this.guest = guest;
		this.reservation = reservation;
		this.accepted = accepted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	@Override
	public String toString() {
		return "GuestReservation [guest=" + guest + ", reservation=" + reservation + "]";
	}

}
