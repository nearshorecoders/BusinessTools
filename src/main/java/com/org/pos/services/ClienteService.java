package com.org.pos.services;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pos.model.Cliente;
import com.org.pos.model.Productos;
import com.org.pos.model.Usuario;
import com.org.pos.repository.ClienteRepository;
import com.org.pos.repository.UserRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Integer createClient(Map<String,String> body) {
		
		String nombre=body.get("nombre");
		String apellidoP=body.get("apellidoP");
		String apellidoM=body.get("apellidoM");
		String dir=body.get("direccion");
		String tel=body.get("telefono");
		
		return clienteRepository.agregarCliente(nombre, apellidoP, apellidoM, dir, tel);
		
	}
	
	
	public  Map<String, Object> listarClientes(Principal principal){
		Map<String,Object> result=new HashMap<String,Object>();
		Usuario u=userRepository.findByUser(principal.getName());

		List<Cliente> clientes=clienteRepository.listarClientes(u.getId());
		
		result.put("listaClientesTodos", clientes);
		
		return result;
		
	}
	
	
	public  Integer modificarCliente(Cliente cliente){
		
		return clienteRepository.modificarCliente(cliente);
		
	}
	
	public Map<String, Object> listarClientesByName (String nombre,String apellidoP, String apellidoM){
		 Map<String, Object> result=new HashMap<String,Object>();
		 List<Cliente> listaClientes=clienteRepository.buscarCliente(nombre, apellidoP, apellidoM);
		 result.put("listadoClientesEncontrados", listaClientes);
		 return result;
	}

	public  Integer desactivarCliente(Integer idCliente){
		
		return clienteRepository.desactivarCliente(idCliente);
		
	}
	
}
