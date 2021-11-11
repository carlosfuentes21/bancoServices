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
@Table(name="transaccion")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int trasaccion_id;
	
	@ManyToOne()
  	@JoinColumn(name="cuenta_id")
  	@JsonBackReference
  	private Bill cuenta_id;
	
	private String trasaccion_tipo;
	private String trasaccion_monto;
	
	public int getTrasaccion_id() {
		return trasaccion_id;
	}
	
	public void setTrasaccion_id(int trasaccion_id) {
		this.trasaccion_id = trasaccion_id;
	}
	
	public Bill getCuenta_id() {
		return cuenta_id;
	}
	
	public void setCuenta_id(Bill cuenta_id) {
		this.cuenta_id = cuenta_id;
	}
	
	public String getTrasaccion_tipo() {
		return trasaccion_tipo;
	}
	
	public void setTrasaccion_tipo(String trasaccion_tipo) {
		this.trasaccion_tipo = trasaccion_tipo;
	}
	
	public String getTrasaccion_monto() {
		return trasaccion_monto;
	}
	
	public void setTrasaccion_monto(String trasaccion_monto) {
		this.trasaccion_monto = trasaccion_monto;
	}

}
