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
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BillRepository billRepository;

	@PostMapping("/register")
	public Map<String, Object> create(@RequestBody User user) {

		try {
			if (Utils.validarCorreo(user.getUser_email())) {
				if (Utils.validarContra(user.getUser_password())) {

					userRepository.save(user);
					Bill cuenta = new Bill();
					cuenta.setUser_id(user);
					cuenta.setBill_number(Long.valueOf(Utils.numeroCuenta()));
					cuenta.setBill_amount(1000000);
					billRepository.save(cuenta);

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

			loginUsuario = userRepository.login(user.getUser_email(), user.getUser_password());
			bill = billRepository.findByUsuarioId(loginUsuario.getUser_id());

			if (loginUsuario.getUser_estate() == "HABILITADO") {
				return Utils.mapear(false, "El usuario no esta habilitado", null);
			}

			usuarioActivo.setUser_id(loginUsuario.getUser_id());
			usuarioActivo.setUser_email(loginUsuario.getUser_email());
			usuarioActivo.setUser_name(loginUsuario.getUser_name());
			usuarioActivo.setUser_identification(loginUsuario.getUser_identification());
			usuarioActivo.setUser_estate(loginUsuario.getUser_estate());

			billDto.setBill_number(bill.getBill_number());
			billDto.setBill_amount(bill.getBill_amount());
			usuarioActivo.setBill(billDto);

			return Utils.mapear(true, "Ingreso exitoso!", usuarioActivo);
		} catch (Exception e) {
			System.out.println("Error al login--->" + e);
			return Utils.msg(false, "Error al ingresar.");
		}
	}

}
