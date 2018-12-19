package com.org.pos.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.pos.model.Usuario;
import com.org.pos.repository.UserRepository;
import com.org.pos.services.CajaService;

@Controller
public class CajaController {

	@Autowired
	CajaService cajaService;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping(value = "/getBalanceByDateAndSucursal")
	public @ResponseBody ResponseEntity<?> getAllBalanceDetails(Principal principal,@RequestParam String fi,@RequestParam String ff) {
		try {	
			Usuario u=userRepository.findByUser(principal.getName());
			
			return new ResponseEntity<Map<String, Object>>(cajaService.obtenerBalanceDelDia(fi, ff, u.getSucursal_idsucursal()), HttpStatus.OK);
		}catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping(value = "/getAllCortesDeCaja")
	public @ResponseBody ResponseEntity<?> getAllCortesDeCaja(Principal principal,@RequestParam String fi,@RequestParam String ff) {
		try {	
			Usuario u=userRepository.findByUser(principal.getName());
			
			return new ResponseEntity<Map<String, Object>>(cajaService.obtenerCortesDeCaja(fi, ff, u.getSucursal_idsucursal()), HttpStatus.OK);
		}catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping(value = "/getGastosByDayAndSucursal")
	public @ResponseBody ResponseEntity<?> getAllGastosDetails(Principal principal,@RequestParam String fi,@RequestParam String ff) {
		try {	
			Usuario u=userRepository.findByUser(principal.getName());
			
			return new ResponseEntity<Map<String, Object>>(cajaService.obtenerGastosDelDia(fi, ff, u.getSucursal_idsucursal()), HttpStatus.OK);
			
		}catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping(value = "/realizarCorteDeCaja")
	public @ResponseBody ResponseEntity<?> realizarCorteDeCaja(Principal principal,@RequestParam String balance) {
		try {	
			Map<String,Object> resultadosObtenidos=new HashMap();
			Usuario u=userRepository.findByUser(principal.getName());
			Double balanceD=Double.parseDouble(balance);
			Integer resultado=cajaService.realizarCorteDeCaja(balanceD, u.getSucursal_idsucursal());
			resultadosObtenidos.put("result", resultado);
			return new ResponseEntity<Map<String, Object>>(resultadosObtenidos, HttpStatus.OK);
		}catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping(value = "/registrarGasto")
	public @ResponseBody ResponseEntity<?> registrarGasto(Principal principal,@RequestParam String cantidad,@RequestParam String descripcion) {
		try {	
			Map<String,Object> resultado=new HashMap<String,Object>();
			Usuario u=userRepository.findByUser(principal.getName());
			
			resultado.put("retiroGenerado",cajaService.registrarRetiro(cantidad, descripcion, u.getSucursal_idsucursal(), u.getId().toString()));
			
			return new ResponseEntity<Map<String, Object>>(resultado, HttpStatus.OK);
		}catch(Exception e) {
			return null;
		}
	}
	
}
