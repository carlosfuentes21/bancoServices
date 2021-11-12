package com.fuentes.bancos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fuentes.bancos.VO.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>{
	
	@Query(nativeQuery = true,value = " SELECT * FROM bill WHERE user_id=?1")
    Bill findByUsuarioId(int userId);
	
	@Query(nativeQuery = true,value = " SELECT * FROM bill WHERE bill_number=?1")
    Bill findNumeroCuenta(String numberBill);
	
}
