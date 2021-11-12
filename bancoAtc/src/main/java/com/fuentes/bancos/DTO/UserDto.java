package com.fuentes.bancos.DTO;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fuentes.bancos.VO.Bill;

public class UserDto {
	
	private int user_id;
	private String user_name;
	private String user_identification;
	private String user_email;
	private String user_estate;
	
	@JsonManagedReference
    private BillDto bilDto;

	@OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
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

	public String getUser_estate() {
		return user_estate;
	}

	public void setUser_estate(String user_estate) {
		this.user_estate = user_estate;
	}

	public BillDto getBilDto() {
		return bilDto;
	}

	public void setBilDto(BillDto bilDto) {
		this.bilDto = bilDto;
	}

}
