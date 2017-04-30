package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{
	
    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "email")
	private String email;
    
    @Column(name = "password")
	private String password;
    
    @Column(name = "type")
	private TypeOfUser type;
	
	public User(String email, String password, TypeOfUser type) {
		super();
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	protected User() {

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
	
	
	
}
