package com.example.domain;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Guest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private String password;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Transient
	private HashMap<Long, Guest> friends;
	@Transient
	private HashMap<Long, Guest> requests;
	@Transient
	private HashMap<Long, Restaurant> visited;

	public Guest() {
	}

	public Guest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.friends = new HashMap<>();
		this.visited = new HashMap<>();
		this.requests = new HashMap<>();
	}

	public Guest(String email, String password, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.friends = new HashMap<>();
		this.visited = new HashMap<>();
		this.requests = new HashMap<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public HashMap<Long, Guest> getFriends() {
		return friends;
	}

	public void setFriends(HashMap<Long, Guest> friends) {
		this.friends = friends;
	}

	public HashMap<Long, Restaurant> getVisited() {
		return visited;
	}

	public void setVisited(HashMap<Long, Restaurant> visited) {
		this.visited = visited;
	}

	public HashMap<Long, Guest> getRequests() {
		return requests;
	}

	public void setRequests(HashMap<Long, Guest> requests) {
		this.requests = requests;
	}

	@Override
	public String toString() {
		return "Guest [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}
}
