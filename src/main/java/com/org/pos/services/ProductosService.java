package com.org.pos.services;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pos.model.Productos;
import com.org.pos.repository.ProductosRepository;

@Service
public class ProductosService {

	@Autowired 
	ProductosRepository productosRepository;
	
	public Map<String,Object> insertarProducto(Principal principal,Productos producto){
		Map<String,Object> result=new HashMap<String,Object>();
		
		
		
		return result;
	}
	
}
