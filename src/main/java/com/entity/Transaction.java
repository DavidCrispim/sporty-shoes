package com.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.TemporalType.DATE;

@Entity()
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int tid;
	private LocalDateTime tDateTime;
	private int quantity;
	private float value;
	  
	@ManyToOne
	@JoinColumn(name = "pid")
	private Product product;
		
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public LocalDateTime gettDateTime() {
		return tDateTime;
	}

	public void settDateTime(LocalDateTime tDateTime) {
		this.tDateTime = tDateTime;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
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
