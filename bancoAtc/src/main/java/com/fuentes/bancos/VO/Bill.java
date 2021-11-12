package com.fuentes.bancos.VO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="bill")
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bill_id;
	
	@OneToOne()
  	@JoinColumn(name="user_id")
  	@JsonBackReference
  	private User user_id;
	
	private Long bill_number;
	private int bill_amount;
	
	@OneToMany(mappedBy = "bill_id", cascade = CascadeType.ALL)
	@JsonManagedReference
    private List<Transaction> transaction;

	public int getBill_id() {
		return bill_id;
	}

	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public Long getBill_number() {
		return bill_number;
	}

	public void setBill_number(Long bill_number) {
		this.bill_number = bill_number;
	}

	public int getBill_amount() {
		return bill_amount;
	}

	public void setBill_amount(int bill_amount) {
		this.bill_amount = bill_amount;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
	
}
