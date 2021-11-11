package com.fuentes.bancos.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuentes.bancos.Utils;
import com.fuentes.bancos.DTO.BillDto;
import com.fuentes.bancos.DTO.UserDto;
import com.fuentes.bancos.VO.Bill;
import com.fuentes.bancos.VO.User;
import com.fuentes.bancos.repository.BillRepository;
import com.fuentes.bancos.repository.UserRepository;


@RestController
@RequestMapping(path = "/usuario")
public class UserController {
	
	@Autowired
	private UserRepository rUsuarioRepository;
	@Autowired
	private BillRepository rBillRepository;
	
	
	@PostMapping("/register")
	public Map<String, Object> create(@RequestBody User user) {
		
		try {
			if (Utils.validarCorreo(user.getUsuario_correo())) {
				if (Utils.validarContra(user.getUsuario_contra())) {
					
					rUsuarioRepository.save(user);
					Bill cuenta = new Bill();
					cuenta.setUsuario_id(user);
					cuenta.setCuenta_numero(Long.valueOf(Utils.numeroCuenta()));
					cuenta.setCuenta_saldo(1000000);
					rBillRepository.save(cuenta);
					
				} else {
					return Utils.msg(false, "La contraseña no es valida.");
				}
			} else {
				return Utils.msg(false, "El correo no es valido.");
			}
			return Utils.msg(true, "Registro exitoso");
		} catch (Exception e) {
			return Utils.msg(false, "Error al registrar el usuario");
		}
		
	}
	
	
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody User user) {
		
		User loginUsuario = null;
		UserDto usuarioActivo = new UserDto();
		BillDto billDto = new BillDto();
		Bill bill = new Bill();
		try {
			
			loginUsuario = rUsuarioRepository.login(user.getUsuario_correo(), user.getUsuario_contra());
			bill =  rBillRepository.findByUsuarioId(loginUsuario.getUsuario_id());
			
			if (loginUsuario.getUsuario_estado() == "HABILITADO") {
				return Utils.mapear(false, "El usuario no esta habilitado", null);
			}
			
			usuarioActivo.setUsuario_id(loginUsuario.getUsuario_id());
			usuarioActivo.setUsuario_correo(loginUsuario.getUsuario_correo());
			usuarioActivo.setUsuario_nombre(loginUsuario.getUsuario_nombre());
			usuarioActivo.setUsuario_identificacion(loginUsuario.getUsuario_identificacion());
			usuarioActivo.setUsuario_estado(loginUsuario.getUsuario_estado());
			
			billDto.setCuenta_numero(bill.getCuenta_numero());
			billDto.setCuenta_saldo(bill.getCuenta_saldo());
			usuarioActivo.setCuenta(billDto);
			
			return Utils.mapear(true, "Ingreso exitoso!", usuarioActivo);
		} catch (Exception e) {
			System.out.println("Error al login--->"+e);
			return Utils.msg(false, "Error al ingresar.");
		}
	}

}
