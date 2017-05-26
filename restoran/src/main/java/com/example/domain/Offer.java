package com.example.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Offer implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private int quality ;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private Offer_status status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supply_id")
	private Supply supply;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
	public Offer(int quality, double price, Offer_status status) {
		super();
		this.quality = quality;
		this.price = price;
		this.status = status;
	}
	
	public Offer() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Offer_status getStatus() {
		return status;
	}

	public void setStatus(Offer_status status) {
		this.status = status;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", quality=" + quality + ", price=" + price + ", status=" + status + ", supply="
				+ supply + ", supplier=" + supplier + "]";
	}

	
	
}
