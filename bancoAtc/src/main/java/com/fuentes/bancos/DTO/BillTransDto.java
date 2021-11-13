package com.fuentes.bancos.DTO;

public class BillTransDto {
	
	private String numberBill;
	private String typeTransation;
	private String amount;
	
	public String getNumberBill() {
		return numberBill;
	}
	
	public void setNumberBill(String numberBill) {
		this.numberBill = numberBill;
	}
	
	public String getTypeTransation() {
		return typeTransation;
	}
	
	public void setTypeTransation(String typeTransation) {
		this.typeTransation = typeTransation;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
