package com.fuentes.bancos.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuentes.bancos.QRCodeUtil;
import com.fuentes.bancos.Utils;
import com.fuentes.bancos.DTO.NumberBillDto;
import com.fuentes.bancos.VO.Bill;
import com.fuentes.bancos.util.JWTUtil;

@RestController
@RequestMapping(path = "/QR")
public class QrCodeController {
	/**
     * Generar código QR ordinario según url
     */
	
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
    QRCodeUtil qrCodeService;
	
    //@RequestMapping(value = "/createCommonQRCode")
	@PostMapping("/createCommonQRCode")
    public Map<String, Object> createCommonQRCode(@RequestBody NumberBillDto numberBillDto, @RequestHeader(value="Authorization") String token) throws Exception {
        try {
        	if(jwtUtil.validarToken(token)) {
        		//stream = response.getOutputStream();
                // Usa herramientas para generar código QR
                //QRCodeUtil.encode(numberBillDto.getNumberBill(), stream);
        		String result = qrCodeService.crateQRCode("this is qrcode",200,200);
        		return Utils.msgQr(true, "success", result);
        	}
        	return Utils.msg(false, "token invalido");
            
        } catch (Exception e) {
            e.getStackTrace();
            return Utils.msg(false, "fail");
        }
    }

    /**
           * Generar código QR con logo según url
     */
    @RequestMapping(value = "/createLogoQRCode")
    public void createLogoQRCode(HttpServletResponse response, String url) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() 
                    + "templates" + File.separator + "logo.jpg";
            // Usa herramientas para generar código QR
            QRCodeUtil.encode(url, logoPath, stream, true);
            
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}
