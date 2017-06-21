package com.example.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DrinkMenu implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToMany
	private Set<DrinkMenuItem> items;
	@Column
	private Date dateUpdate;
	
	public DrinkMenu(){
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<DrinkMenuItem> getItems() {
		return items;
	}
	public void setItems(Set<DrinkMenuItem> items) {
		this.items = items;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	@Override
	public String toString() {
		return "DrinkMenu [id=" + id + ", items=" + items + ", dateUpdate=" + dateUpdate + "]";
	}
	
}
