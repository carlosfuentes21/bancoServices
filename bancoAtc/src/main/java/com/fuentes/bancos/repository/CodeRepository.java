package com.fuentes.bancos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fuentes.bancos.VO.CodeTransaction;

public interface CodeRepository extends JpaRepository<CodeTransaction, Integer>{
	
	@Query(nativeQuery = true,value = "SELECT * FROM code_transaction WHERE number_bill=?1 AND code=?2")
    CodeTransaction findNumeroCuenta(String numberBill, String codeAut);
	
}
