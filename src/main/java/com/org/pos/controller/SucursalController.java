package com.org.pos.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.org.pos.services.SucursalService;

@Controller
public class SucursalController {

	@Autowired
	SucursalService sucursalService;
	
	
	@PostMapping("/createSucursal")
	public ResponseEntity<?> getMenu(@RequestBody Map<String, String> body,Principal principal) {
		//Clientos Cliento=new Clientos();
		 //Map<String,Object> flujoResult=ClientosService.insertarCliento(principal,Cliento);
		//return new ResponseEntity<Map<String,Object>>(flujoResult,HttpStatus.OK);
		return null;
	}
	
	@GetMapping("/getSucursal/{id}")
	public Object getSucursal(@PathVariable Long id) {
		try {
			return new Object();
		}catch(Exception e) {
			return null;
		}
	}
	
	@GetMapping("/updateSucursal/{id}")
	public Object updateSucursal(@PathVariable Long id) {
		try {
			return new Object();
		}catch(Exception e) {
			return null;
		}
	}
	
	@GetMapping("/getSucursal/{idUser}")
	public Object getSucursalByLoguedUser(@PathVariable String idUser) {
		try {
			return new Object();
		}catch(Exception e) {
			return null;
		}
	}
	
}
