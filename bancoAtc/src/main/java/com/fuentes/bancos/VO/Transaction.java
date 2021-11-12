package com.fuentes.bancos.VO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transaction_id;
	
	@ManyToOne()
  	@JoinColumn(name="bill_id")
  	@JsonBackReference
  	private Bill bill_id;
	
	private String transaction_type;
	private String transaction_amount;
	private String transaction_description;
	private String transaction_date;
	private boolean transaction_estate;
	
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public Bill getBill_id() {
		return bill_id;
	}
	public void setBill_id(Bill bill_id) {
		this.bill_id = bill_id;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public String getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(String transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public String getTransaction_description() {
		return transaction_description;
	}
	public void setTransaction_description(String transaction_description) {
		this.transaction_description = transaction_description;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public boolean isTransaction_estate() {
		return transaction_estate;
	}
	public void setTransaction_estate(boolean transaction_estate) {
		this.transaction_estate = transaction_estate;
	}
	
}
