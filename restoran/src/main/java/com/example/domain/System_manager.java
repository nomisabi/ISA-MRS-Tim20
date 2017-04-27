package com.example.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class System_manager {

	@NotEmpty(message = "Email je obavezna.")
    private String email;
	
	@NotEmpty(message = "Password je obavezna.")
    private String password;
	
	
	public System_manager() {

    }

	public System_manager(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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
	
	
}
