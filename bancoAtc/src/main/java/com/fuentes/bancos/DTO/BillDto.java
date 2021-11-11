package com.fuentes.bancos.DTO;

import javax.persistence.OneToMany;

public class BillDto {
	
	private Long cuenta_numero;
	private int cuenta_saldo;
	
	public Long getCuenta_numero() {
		return cuenta_numero;
	}
	
	public void setCuenta_numero(Long cuenta_numero) {
		this.cuenta_numero = cuenta_numero;
	}
	
	public int getCuenta_saldo() {
		return cuenta_saldo;
	}
	
	public void setCuenta_saldo(int cuenta_saldo) {
		this.cuenta_saldo = cuenta_saldo;
	}
	
}
