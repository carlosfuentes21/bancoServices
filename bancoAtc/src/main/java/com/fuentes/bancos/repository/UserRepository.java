package com.fuentes.bancos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fuentes.bancos.VO.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(nativeQuery = true,value = " SELECT * FROM usuario WHERE usuario_correo=?1 AND usuario_contra=?2")
	User login(String correo, String contra);
	
}
