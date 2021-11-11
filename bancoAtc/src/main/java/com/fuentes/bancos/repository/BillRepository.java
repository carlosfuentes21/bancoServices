package com.fuentes.bancos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fuentes.bancos.VO.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>{
	
	@Query(nativeQuery = true,value = " SELECT * FROM cuenta WHERE usuario_id=?1")
    Bill findByUsuarioId(int usuarioId);
	
	@Query(nativeQuery = true,value = " SELECT * FROM cuenta WHERE cuenta_numero=?1")
    Bill findNumeroCuenta(String numeroCuenta);
	
}
