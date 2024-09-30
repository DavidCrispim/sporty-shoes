package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

@Entity()
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int tid;
	private LocalDateTime tDateTime;
	private int quantity;
	  
	@ManyToOne
	@JoinColumn(name = "pid")
	private Product product;
		
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public LocalDateTime getTDateTime() {
		return tDateTime;
	}

	public void setTDateTime(LocalDateTime tDateTime) {
		this.tDateTime = tDateTime;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Transaction [tid=" + tid + ", tDateTime=" + tDateTime + ", quantity=" + quantity + "]";
	}
}
