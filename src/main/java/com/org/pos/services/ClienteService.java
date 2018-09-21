package com.org.pos.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pos.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRespository;
	
	public Integer createClient(Map<String,String> body) {
		
		String nombre=body.get("nombre");
		String apellidoP=body.get("apellidoP");
		String apellidoM=body.get("apellidoM");
		String dir=body.get("direccion");
		String tel=body.get("direccion");
		
		return clienteRespository.agregarCliente(nombre, apellidoP, apellidoM, dir, tel);
		
	}
	
}
