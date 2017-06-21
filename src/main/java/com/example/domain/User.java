package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userT")
public class User implements Serializable{
	
    private static final long serialVersionUID =  1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
	private String email;
    
    @Column(nullable = false)
	private String password;
    
    @Column
	private TypeOfUser type;
	
	public User(String email, String password, TypeOfUser type) {
		super();
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	public User() {

	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", type=" + type + "]";
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

	public TypeOfUser getType() {
		return type;
	}

	public void setType(TypeOfUser type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
