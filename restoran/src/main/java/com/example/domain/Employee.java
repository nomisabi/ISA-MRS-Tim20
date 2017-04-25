package com.example.domain;

import java.util.Date;

public class Employee {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date date;
	private int numbC;
	private int numbS;
	private TypeEmployee type;
	
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
	
	
	
}
