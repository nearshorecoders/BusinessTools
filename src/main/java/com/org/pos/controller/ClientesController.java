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
import com.org.pos.utils.MailSender;


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
	
	
	@GetMapping(value = "/getAllClients")
	public @ResponseBody ResponseEntity<?> getAllClients(Principal principal) {
		try {	
			return new ResponseEntity<Map<String, Object>>(clientesService.listarClientes(principal), HttpStatus.OK);
		}catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping("/getClientByName")
	public @ResponseBody ResponseEntity<?> getClienteByName(Principal principal, @RequestParam String nombre,@RequestParam String apellidop, @RequestParam String apellidom) {
		try {
			return new ResponseEntity<Map<String, Object>>(clientesService.listarClientesByName(nombre, apellidop, apellidom), HttpStatus.OK);
		}catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping("/getClientDetail")
	public @ResponseBody ResponseEntity<?> getClienteById(Principal principal, @RequestParam String clientId) {
		try {
			Integer clienteId=Integer.parseInt(clientId);
			return new ResponseEntity<Map<String, Object>>(clientesService.getDetailByCliente(principal,clienteId), HttpStatus.OK);
		}catch(Exception e) {
			return null;
		}
	}
	@PostMapping("/getEdoCuentaDetail")
	public @ResponseBody ResponseEntity<?> getEstadoDeCuentaDetail(Principal principal, @RequestParam String clientId,@RequestParam String productId) {
		try {
			Integer clienteId=Integer.parseInt(clientId);
			Integer productoId=Integer.parseInt(clientId);
			return new ResponseEntity<Map<String, Object>>(clientesService.getDetailHistorialPagosByProduct(principal, clienteId, productoId), HttpStatus.OK);
		}catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping("/getEdoCuentaByStatus")
	public @ResponseBody ResponseEntity<?> getEstadoDeCuentaBystatus(Principal principal, @RequestParam String clientId,@RequestParam String status) {
		try {
			Integer clienteId=Integer.parseInt(clientId);
			Integer statusId=Integer.parseInt(status);
			return new ResponseEntity<Map<String, Object>>(clientesService.getDetailHistorialPagosByProduct(principal, clienteId, statusId), HttpStatus.OK);
		}catch(Exception e) {
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
	
	@PostMapping(value = "/sendInvoice")
	public @ResponseBody ResponseEntity<String> getReporteVentas() {
		MailSender mailSender=new MailSender();
		 //String email1="opticamirosc76@hotmail.com";
		 String email1="alfred0823@hotmail.com";
		
//		try {
//			mailSender.enviarCorreo("hotmail", "Factura MIROSC venta 2018786645",email1, null, "Se ha enviado la factura que requirio via telefonica");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return new ResponseEntity<String>("OK", HttpStatus.OK);
		
	}
}
