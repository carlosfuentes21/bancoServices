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
@Table(name="cuenta")
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cuenta_id;
	
	@OneToOne()
  	@JoinColumn(name="usuario_id")
  	@JsonBackReference
  	private User usuario_id;
	
	private Long cuenta_numero;
	private int cuenta_saldo;
	
	@OneToMany(mappedBy = "cuenta_id", cascade = CascadeType.ALL)
	@JsonManagedReference
    private List<Transaction> transaccion;
	
	public int getCuenta_id() {
		return cuenta_id;
	}
	
	public void setCuenta_id(int cuenta_id) {
		this.cuenta_id = cuenta_id;
	}
	
	public User getUsuario_id() {
		return usuario_id;
	}
	
	public void setUsuario_id(User usuario_id) {
		this.usuario_id = usuario_id;
	}
	
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
	
	public List<Transaction> getTransaccion() {
		return transaccion;
	}
	
	public void setTransaccion(List<Transaction> transaccion) {
		this.transaccion = transaccion;
	}
	
}
