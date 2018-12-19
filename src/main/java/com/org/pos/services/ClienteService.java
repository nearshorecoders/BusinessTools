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
import com.org.pos.repository.EstadoDeCuentaRepository;
import com.org.pos.repository.UserRepository;
import com.org.pos.repository.VentasRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VentasRepository ventasRepository;
	
	@Autowired
	EstadoDeCuentaRepository estadoDeCuentaRepository;
	
	public Integer createClient(Map<String,String> body) {
		
		String nombre=body.get("nombre");
		String apellidoP=body.get("apellidoP");
		String apellidoM=body.get("apellidoM");
		String dir=body.get("direccion");
		String tel=body.get("telefono");
		String email=body.get("email");
		String telefono2=body.get("telefono2");
		return clienteRepository.agregarCliente(nombre, apellidoP, apellidoM, dir, tel,email,telefono2);
		
	}
	
	public  Map<String, Object> getDetailByCliente(Principal principal,Integer idCliente){
		Map<String, Object> resultado=new HashMap<String,Object>();
		resultado.put("historialVentas", ventasRepository.generarReporteVentasByClient(idCliente,0.0));
		resultado.put("estadoDeCuenta", estadoDeCuentaRepository.getEstadoDeCuentaByClient(idCliente));
		return resultado;
	}
	
	public  Map<String, Object> getDetailHistorialPagosByProduct(Principal principal,Integer idCliente,Integer idProducto){
		Map<String, Object> resultado=new HashMap<String,Object>();
		resultado.put("historialPagosDetalle", estadoDeCuentaRepository.getHistoricDetailByProduct(idCliente,idProducto));
		return resultado;
	}
	
	public Map<String,Object> getEstadoDeCuentaByClientAndStatus(Integer clientID,Integer status) {
		Map<String, Object> resultado=new HashMap<String,Object>();
		resultado.put("estadoDeCuentaPorEstatus", estadoDeCuentaRepository.getEstadoDeCuentaByClientAndStatus(clientID,status));
		return resultado;		
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
