package com.org.pos.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.pos.model.Usuario;
import com.org.pos.services.UsuariosService;

@Controller
public class UsuariosController {
	@Autowired
	UsuariosService usuariosService;
	
	@PostMapping("/createUsuario")
	public ResponseEntity<?> getMenu(@RequestBody Map<String, String> body,Principal principal) {
		Usuario Usuario=new Usuario();
		 //Map<String,Object> flujoResult=usuariosService.insertarUsuarioo(principal,Usuarioo);
		//return new ResponseEntity<Map<String,Object>>(flujoResult,HttpStatus.OK);
		return null;
	}
	
	@GetMapping("/getUsuario/{id}")
	public Object getToEvalById(@PathVariable Long id) {
		try {
			return new Object();
		}catch(Exception e) {
			return null;
		}
	}
	
	
	@PostMapping(value = "/getAllUsuarios")
	public @ResponseBody ResponseEntity<List<Map<String, Object>>> getAllUsuarios(@RequestParam String str, @RequestParam int type) {
		if(type == 0) {
			//return new ResponseEntity<List<Map<String, Object>>>(ticketRepository.getUserByName(str), HttpStatus.OK);
			return null;
		}else {
			//return new ResponseEntity<List<Map<String, Object>>>(ticketRepository.getUserByEmail(str), HttpStatus.OK);
			return null;
		}
		
	}
	
	@PutMapping(value = "/updateUsuario/{id}")
	public Object updateUsuario(@PathVariable Long id) {
		try {
			return new Object();
		}catch(Exception e) {
			return null;
		}
	}
	
	@DeleteMapping(value = "/deleteUsuario/{id}")
	public Object deleteUsuario(@PathVariable Long id) {
		try {
			//we dont delete producs to preserve the integrity of historical data
			//instead we only deactivate producs to not be showed in the list
			return new Object();
		}catch(Exception e) {
			return null;
		}
	}
}
