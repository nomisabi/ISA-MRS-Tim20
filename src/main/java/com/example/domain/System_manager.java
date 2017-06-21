package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sysman")
public class System_manager {

    private static final long serialVersionUID =  1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
	
    @Column
	private String firstName;
	
    @Column
	private String lastName;
	
	public System_manager() {

    }

	public System_manager(String email, String password, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
