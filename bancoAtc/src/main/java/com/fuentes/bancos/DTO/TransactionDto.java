package com.fuentes.bancos.DTO;

public class TransactionDto extends BillTransDto{
	
	private String numeroCuentaDestino;

	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}
	
}
