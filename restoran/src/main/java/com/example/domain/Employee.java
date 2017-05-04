package com.example.domain;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;

@Entity
public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column
	private Date date;
	@Column
	private int numbC;
	@Column
	private int numbS;
	@Column
	private TypeEmployee type;
	@Column
	private boolean active;
	
	public Employee(){
		
	}
	
	public Employee(String firstName, String lastName, String email, String password, Date date, int numbC, int numbS,
			TypeEmployee type) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.date = date;
		this.numbC = numbC;
		this.numbS = numbS;
		this.type = type;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNumbC() {
		return numbC;
	}
	public void setNumbC(int numbC) {
		this.numbC = numbC;
	}
	public int getNumbS() {
		return numbS;
	}
	public void setNumbS(int numbS) {
		this.numbS = numbS;
	}

	public TypeEmployee getType() {
		return type;
	}

	public void setType(TypeEmployee type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
