package com.fuentes.bancos.DTO;

import javax.persistence.OneToMany;

public class BillDto {
	
	private Long bill_number;
	private int bill_amount;
	
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
	
}
