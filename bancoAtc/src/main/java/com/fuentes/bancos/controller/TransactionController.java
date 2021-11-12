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
	BillRepository billRepository;
	@Autowired
	TransactionRepository transactionRepository;
	
	@PutMapping(path = "/retirement")
	public Map<String, Object> retiro(@RequestBody BillTransDto transaccionDto) {
		Bill cuenta = null;
		Transaction transaccion = new Transaction();
		int monto = Integer.parseInt(transaccionDto.getAmount());
		cuenta = billRepository.findNumeroCuenta(transaccionDto.getNumberBill());
		if (cuenta != null) {
			try {
				transaccion.setBill_id(cuenta);
				transaccion.setTransaction_type(transaccionDto.getTypeTransation());
				transaccion.setTransaction_amount(transaccionDto.getAmount());
				transaccion.setTransaction_description("RETIRO DE PC");
				transaccion.setTransaction_date("31 Oct 21 10:00");
				transaccion.setTransaction_estate(false);
				if (cuenta.getBill_amount() >= monto) {
					cuenta.setBill_amount(cuenta.getBill_amount() - monto);
					billRepository.save(cuenta);
					transactionRepository.save(transaccion);
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
		int monto = Integer.parseInt(transferenciaDto.getAmount());
		cuenta = billRepository.findNumeroCuenta(transferenciaDto.getNumberBill());
		cuentaDestino = billRepository.findNumeroCuenta(transferenciaDto.getNumberBillDestiny());
		if (cuentaDestino != null) {
			try {
				transaccion.setBill_id(cuenta);
				
				if (cuenta.getBill_amount() >= monto) {
					cuenta.setBill_amount(cuenta.getBill_amount() - monto);
					transaccion.setTransaction_amount(transferenciaDto.getAmount());
					transaccion.setTransaction_type(transferenciaDto.getTypeTransation());
					transaccion.setTransaction_description("PAGO DE PC");
					transaccion.setTransaction_date("31 Oct 21 10:00");
					transaccion.setTransaction_estate(false);
					transactionRepository.save(transaccion);
					billRepository.save(cuenta);
					
					transaccion = new Transaction();
					transaccion.setTransaction_estate(true);
					transaccion.setBill_id(cuentaDestino);
					cuentaDestino.setBill_amount(cuentaDestino.getBill_amount() + monto);
					transaccion.setTransaction_amount(transferenciaDto.getAmount());
					transaccion.setTransaction_type(transferenciaDto.getTypeTransation());
					transaccion.setTransaction_description("PAGO DE PC");
					transaccion.setTransaction_date("31 Oct 21 10:00");
					billRepository.save(cuentaDestino);
					transactionRepository.save(transaccion);
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
	
	@PostMapping("/getTransfers")
	public Map<String, Object> mostrarTransferencias(@RequestBody BillTransDto cuenta) {
		try {
			
			Bill bill = billRepository.findNumeroCuenta(cuenta.getNumberBill());
			
			if(bill != null) {
				if(bill.getTransaction() != null && bill.getTransaction().size()>0) {
					return Utils.mapear(true, "success", bill.getTransaction());
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
