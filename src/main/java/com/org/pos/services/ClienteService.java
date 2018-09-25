package com.org.pos.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pos.model.Cliente;
import com.org.pos.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public Integer createClient(Map<String,String> body) {
		
		String nombre=body.get("nombre");
		String apellidoP=body.get("apellidoP");
		String apellidoM=body.get("apellidoM");
		String dir=body.get("direccion");
		String tel=body.get("telefono");
		
		return clienteRepository.agregarCliente(nombre, apellidoP, apellidoM, dir, tel);
		
	}
	
	
	public  Map<String, Object> listarClientes(Integer idLoguedUser){
		
		return clienteRepository.listarClientes(idLoguedUser);
		
	}
	
	
	public  Integer modificarCliente(Cliente cliente){
		
		return clienteRepository.modificarCliente(cliente);
		
	}

	public  Integer desactivarCliente(Integer idCliente){
		
		return clienteRepository.desactivarCliente(idCliente);
		
	}
	
}
