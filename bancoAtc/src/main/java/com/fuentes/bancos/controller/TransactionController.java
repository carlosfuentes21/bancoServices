package com.fuentes.bancos.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuentes.bancos.Utils;
import com.fuentes.bancos.DTO.BillTransDto;
import com.fuentes.bancos.DTO.TransactionDto;
import com.fuentes.bancos.VO.Bill;
import com.fuentes.bancos.VO.Transaction;
import com.fuentes.bancos.repository.BillRepository;
import com.fuentes.bancos.repository.TransactionRepository;


@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {
	
	@Autowired
	BillRepository rCuentaRepositorio;
	@Autowired
	TransactionRepository rTransaccionRepositorio;
	
	@PutMapping(path = "/retirement")
	public Map<String, Object> retiro(@RequestBody BillTransDto transaccionDto) {
		Bill cuenta = null;
		Transaction transaccion = new Transaction();
		int monto = Integer.parseInt(transaccionDto.getMonto());
		cuenta = rCuentaRepositorio.findNumeroCuenta(transaccionDto.getNumeroCuenta());
		if (cuenta != null) {
			try {
				transaccion.setCuenta_id(cuenta);
				transaccion.setTrasaccion_tipo(transaccionDto.getTipoTransaccion());
				transaccion.setTrasaccion_monto(transaccionDto.getMonto());
				if (cuenta.getCuenta_saldo() >= monto) {
					cuenta.setCuenta_saldo(cuenta.getCuenta_saldo() - monto);
					rCuentaRepositorio.save(cuenta);
					rTransaccionRepositorio.save(transaccion);
				} else {
					return Utils.msg(false, "Saldo insuficiente.");
				}
				return Utils.msg(true, "Retiro exitoso.");
			} catch (Exception e) {
				return Utils.msg(false, "Error al retirar el dinero.");
			}
		} else {
			return Utils.msg(false, "No existe una cuenta asociada a ese n�mero.");
		}
	}
	
	
	@PutMapping(path = "/transfer")
	public Map<String, Object> transferencia(@RequestBody TransactionDto transferenciaDto) {
		Bill cuenta = null;
		Bill cuentaDestino = null;
		Transaction transaccion = new Transaction();
		int monto = Integer.parseInt(transferenciaDto.getMonto());
		cuenta = rCuentaRepositorio.findNumeroCuenta(transferenciaDto.getNumeroCuenta());
		cuentaDestino = rCuentaRepositorio.findNumeroCuenta(transferenciaDto.getNumeroCuentaDestino());
		if (cuentaDestino != null) {
			try {
				transaccion.setCuenta_id(cuenta);
				
				if (cuenta.getCuenta_saldo() >= monto) {
					cuenta.setCuenta_saldo(cuenta.getCuenta_saldo() - monto);
					transaccion.setTrasaccion_monto(transferenciaDto.getMonto());
					transaccion.setTrasaccion_tipo(transferenciaDto.getTipoTransaccion());
					rTransaccionRepositorio.save(transaccion);
					rCuentaRepositorio.save(cuenta);
					
					transaccion = new Transaction();
					
					transaccion.setCuenta_id(cuentaDestino);
					cuentaDestino.setCuenta_saldo(cuentaDestino.getCuenta_saldo() + monto);
					transaccion.setTrasaccion_monto(transferenciaDto.getMonto());
					transaccion.setTrasaccion_tipo(transferenciaDto.getTipoTransaccion());
					rCuentaRepositorio.save(cuentaDestino);
					rTransaccionRepositorio.save(transaccion);
				} else {
					return Utils.msg(false, "Saldo insuficiente.");
				}
				return Utils.msg(true, "Transferencia exitosa.");
			} catch (Exception e) {
				return Utils.msg(false, "Error al transferir el dinero.");
			}
		} else {
			return Utils.msg(false, "No existe una cuenta asociada a ese número.");
		}
	}
	
	@PutMapping(path = "/deposit")
	public Map<String, Object> deposito(@RequestBody BillTransDto transaccionDto) {
		Bill cuenta = null;
		Transaction transaccion = new Transaction();
		int monto = Integer.parseInt(transaccionDto.getMonto());
		cuenta = rCuentaRepositorio.findNumeroCuenta(transaccionDto.getNumeroCuenta());
		if (cuenta != null) {
			try {
				transaccion.setCuenta_id(cuenta);
					cuenta.setCuenta_saldo(cuenta.getCuenta_saldo() + monto);
					transaccion.setTrasaccion_monto(transaccionDto.getMonto());
					transaccion.setTrasaccion_tipo(transaccionDto.getTipoTransaccion());
					rCuentaRepositorio.save(cuenta);
					rTransaccionRepositorio.save(transaccion);
				return Utils.msg(true, "Deposito exitoso.");
			} catch (Exception e) {
				return Utils.msg(false, "Error al depositar el dinero.");
			}
		} else {
			return Utils.msg(false, "No existe una cuenta asociada a ese número.");
		}
	}
	
	@PostMapping("/getTransfers")
	public Map<String, Object> mostrarTransferencias(@RequestBody BillTransDto cuenta) {
		try {
			
			Bill bill = rCuentaRepositorio.findNumeroCuenta(cuenta.getNumeroCuenta());
			
			if(bill != null) {
				if(bill.getTransaccion() != null && bill.getTransaccion().size()>0) {
					return Utils.mapear(true, "success", bill.getTransaccion());
				}else {
					return Utils.msg(true, "No existen transacciones.");
				}
			}
			
			return Utils.msg(true, "No se encontro el numero de cuenta.");
			
		}catch (Exception e) {
			return Utils.mapear(false, "Error al mostrar las transacciones.", null);
		}
	}
	
	
}
