package com.example.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Schedule implements Serializable{

	private static final long serialVersionUID =  1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToMany
    private Set<Region> regions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Region> getRegions() {
		return regions;
	}

	public void setRegions(Set<Region> regions) {
		this.regions = regions;
	}

	public Schedule(long id, Set<Region> regions) {
		super();
		this.id = id;
		this.regions = regions;
	}
    
	public Schedule(){
		
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", regions=" + regions + "]";
	}
	
	
    
}
