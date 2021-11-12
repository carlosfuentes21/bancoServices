package com.fuentes.bancos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fuentes.bancos.VO.Bill;
import com.fuentes.bancos.VO.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	
	@Query(nativeQuery = true,value = " SELECT * FROM bill WHERE bill_number=?1")
    Bill findNumeroCuenta(String numberBill);
	
}
