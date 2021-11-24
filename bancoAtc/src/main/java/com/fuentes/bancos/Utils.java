package com.fuentes.bancos;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	
	public static Map<String, Object> mapear(boolean status, String msg, Object data) {
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("msg", msg);
		respuesta.put("status", status);
		respuesta.put("data", data);
		return respuesta;
	}
	
	public static Map<String, Object> msgQr(boolean status, String msg, Object data) {
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("msg", msg);
		respuesta.put("status", status);
		respuesta.put("img", data);
		return respuesta;
	}
	
	public static Map<String, Object> mapear(boolean status, String msg, Object data, String token) {
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("msg", msg);
		respuesta.put("status", status);
		respuesta.put("data", data);
		respuesta.put("token", token);
		return respuesta;
	}
	
	public static Map<String, Object> msg(boolean status, String msg) {
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("msg", msg);
		respuesta.put("status", status);
		return respuesta;
	}
	
	public static boolean validarCorreo(String correo) {

		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher mather = pattern.matcher(correo);

		if (mather.find() == true) {
			System.out.println("El email ingresado es válido.");
			return true;
		} else {
			System.out.println("El email ingresado es inválido.");
			return false;
		}
	}
	
	public static boolean validarContra(String contra) {
		char clave;

		byte contNumero = 0;
		byte contLetra = 0;

		for (byte i = 0; i < contra.length(); i++) {

			clave = contra.charAt(i);

			String passValue = String.valueOf(clave);

			if (passValue.matches("[A-Za-z]")) {
				contLetra++;
			}else if (passValue.matches("[0-9]")) {
				contNumero++;
			}
		}
		
		if (contLetra > 0 && contNumero > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static String numeroCuenta() {
		String numero = "";
		for (int i = 0; i < 10; i++) {
			numero += (int) (Math.random() * 10);
		}
		return numero;
	}
	
	public static String codeAut() {
		String numero = "";
		for (int i = 0; i < 6; i++) {
			numero += (int) (Math.random() * 9);
		}
		return numero;
	}
	
	public static Long getDate() {
		return System.currentTimeMillis();
	}
	
	
}
