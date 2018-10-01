package com.org.pos.services;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pos.model.Cliente;
import com.org.pos.model.Productos;
import com.org.pos.repository.ProductosRepository;

@Service
public class ProductosService {

	@Autowired 
	ProductosRepository productosRepository;
	
	public Map<String,Object> insertarProducto(Principal principal,Productos producto){
		Map<String,Object> result=new HashMap<String,Object>();
		
		Integer resultadoInsert=productosRepository.agregarProductoABD(producto);
		
		if(resultadoInsert==1){
			result.put("insertedProduct", producto);
		}
		
		return result;
	}
	
	
	public Map<String,Object> buscarProductoPorDescripcion(Principal principal,String descripcion){
		Map<String,Object> result=new HashMap<String,Object>();
		
		List<Productos> productos=productosRepository.buscarProductoPorDescripcion(descripcion);
		
		result.put("listaProductosPorDescripcion", productos);
		
		return result;
	}
	
	public Map<String,Object> buscarProductoPorCodigo(Principal principal,String codigo){
		Map<String,Object> result=new HashMap<String,Object>();
		
		List<Productos> productos=productosRepository.buscarProductoPorCodigo(codigo);
		
		result.put("listaProductosPorCodigo", productos);
		
		return result;
	}
	
	public Map<String,Object> listarProductos(Principal principal){
		Map<String,Object> result=new HashMap<String,Object>();
		
		List<Productos> productos=productosRepository.listarProductos();
		
		result.put("listaProductosTodos", productos);
		
		return result;
	}
	
}
