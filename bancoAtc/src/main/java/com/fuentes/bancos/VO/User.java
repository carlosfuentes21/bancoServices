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
@Table(name="user")
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private int user_id;
	
	private String user_name;
	private String user_identification;
	private String user_email;
	private String user_password;
	private String user_estate;
	
	
	@OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
	@JsonManagedReference
    private List<Bill> bill;
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_identification() {
		return user_identification;
	}

	public void setUser_identification(String user_identification) {
		this.user_identification = user_identification;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_estate() {
		return user_estate;
	}

	public void setUser_estate(String user_estate) {
		this.user_estate = user_estate;
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
