package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class VerificationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(fetch = FetchType.EAGER, orphanRemoval= true)
	private GuestReservation guestReservation;
	@Column
	private String token;

	public VerificationToken() {
	}

	public VerificationToken(GuestReservation guestReservation, String token) {
		super();
		this.guestReservation = guestReservation;
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GuestReservation getGuestReservation() {
		return guestReservation;
	}

	public void setGuestReservation(GuestReservation guestReservation) {
		this.guestReservation = guestReservation;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
