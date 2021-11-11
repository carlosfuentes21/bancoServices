package com.fuentes.bancos.VO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="usuario")
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private int usuario_id;
	
	private String usuario_nombre;
	private String usuario_identificacion;
	private String usuario_correo;
	private String usuario_contra;
	private String usuario_estado;
	
	
	@OneToMany(mappedBy = "usuario_id", cascade = CascadeType.ALL)
	@JsonManagedReference
    private List<Bill> bill;
	
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
	
	public String getUsuario_contra() {
		return usuario_contra;
	}
	
	public void setUsuario_contra(String usuario_contra) {
		this.usuario_contra = usuario_contra;
	}
	
	public String getUsuario_estado() {
		return usuario_estado;
	}
	
	public void setUsuario_estado(String usuario_estado) {
		this.usuario_estado = usuario_estado;
	}
	
	public List<Bill> getBill() {
		return bill;
	}
	
	public void setBill(List<Bill> bill) {
		this.bill = bill;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;
}
