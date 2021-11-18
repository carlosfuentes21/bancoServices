package com.fuentes.bancos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fuentes.bancos.VO.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	
	/*@Query(nativeQuery = true,value = " SELECT * FROM user WHERE user_email=?1 AND user_password=?2")
	User login(String email, String pass);*/
	
	@Query(nativeQuery = true,value = " SELECT * FROM user WHERE user_email=?1")
	User login(String email);
	
}
