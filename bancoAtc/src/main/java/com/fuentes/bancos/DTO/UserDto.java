package com.fuentes.bancos.DTO;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fuentes.bancos.VO.Bill;

public class UserDto {
	
	private int usuario_id;
	private String usuario_nombre;
	private String usuario_identificacion;
	private String usuario_correo;
	private String usuario_estado;
	
	@JsonManagedReference
    private BillDto cuenta;
	
	@OneToMany(mappedBy = "usuario_id", cascade = CascadeType.ALL)
	
	
	public int getUsuario_id() {
		return usuario_id;
	}
	
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	
	public String getUsuario_nombre() {
		return usuario_nombre;
	}
	
	public void setUsuario_nombre(String usuario_nombre) {
		this.usuario_nombre = usuario_nombre;
	}
	
	public String getUsuario_identificacion() {
		return usuario_identificacion;
	}
	
	public void setUsuario_identificacion(String usuario_identificacion) {
		this.usuario_identificacion = usuario_identificacion;
	}
	
	public String getUsuario_correo() {
		return usuario_correo;
	}
	
	public void setUsuario_correo(String usuario_correo) {
		this.usuario_correo = usuario_correo;
	}
	
	public String getUsuario_estado() {
		return usuario_estado;
	}
	
	public void setUsuario_estado(String usuario_estado) {
		this.usuario_estado = usuario_estado;
	}
	
	public BillDto getCuenta() {
		return cuenta;
	}
	
	public void setCuenta(BillDto cuenta) {
		this.cuenta = cuenta;
	}
	
	
	
	

}
