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

import com.org.pos.model.Cliente;
import com.org.pos.services.ClienteService;


@Controller
public class ClientesController {
	@Autowired
	ClienteService clientesService;
	
	@PostMapping("/createClient")
	public ResponseEntity<?> getMenu(@RequestBody Map<String, String> body,Principal principal) {
		//System.out.println(body);
		//Clientos Cliento=new Clientos();
		//Map<String,Object> flujoResult=ClientosService.insertarCliento(principal,Cliento);
		
		Integer response = clientesService.createClient(body);
		
		return new ResponseEntity<Map<String,String>>(body,HttpStatus.OK);
		//return null;
	}
	
	@GetMapping("/getClient/{id}")
	public Object getToEvalById(@PathVariable Long id) {
		try {
			return null;
		}catch(Exception e) {
			return null;
		}
	}
	
	
	@PostMapping(value = "/getAllClients")
	public @ResponseBody ResponseEntity<List<Map<String, Object>>> getAllClients(@RequestParam String str, @RequestParam int type) {
		if(type == 0) {
			//return new ResponseEntity<List<Map<String, Object>>>(clientesService.listarClientes(idLoguedUser), HttpStatus.OK);
			return null;
		}else {
			//return new ResponseEntity<List<Map<String, Object>>>(ticketRepository.getUserByEmail(str), HttpStatus.OK);
			return null;
		}
		
	}
	
	@PutMapping(value = "/updateClient/{id}")
	public Integer updateClient(@PathVariable Integer id,@PathVariable String varNombre,@PathVariable String varApellidoP, @PathVariable String varApellidoM, @PathVariable String varDireccion,@PathVariable String varTelefono) {
		try {
			
			Cliente cliente=new Cliente();
			cliente.setIdClienteAModificar(id);
			cliente.setVarNombre(varNombre);
			cliente.setVarApellidoP(varApellidoP);
			cliente.setVarApellidoM(varApellidoM);
			cliente.setVarDireccion(varDireccion);
			cliente.setVarTelefono(varTelefono);
			
			return clientesService.modificarCliente(cliente);
			
		}catch(Exception e) {
			return null;
		}
	}
	
	@DeleteMapping(value = "/deleteClient/{id}")
	public Object deleteClient(@PathVariable Integer id) {
		try {
			//we dont delete producs to preserve the integrity of historical data
			//instead we only deactivate producs to not be showed in the list
			return clientesService.desactivarCliente(id);
			
		}catch(Exception e) {
			return null;
		}
	}
}
