package com.fuentes.bancos.VO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="code_transaction")
public class CodeTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private int code_id;
	
	private String code;
	private String number_bill;
	private Long code_time;
	
	public int getCode_id() {
		return code_id;
	}
	public void setCode_id(int code_id) {
		this.code_id = code_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNumber_bill() {
		return number_bill;
	}
	public void setNumber_bill(String number_bill) {
		this.number_bill = number_bill;
	}
	public Long getCode_time() {
		return code_time;
	}
	public void setCode_time(Long code_time) {
		this.code_time = code_time;
	}
	
}
