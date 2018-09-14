package com.org.pos.services;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.org.pos.model.Productos;

@Service
public class ProductosService {

	public Map<String,Object> insertarProducto(Principal principal,Productos producto){
		Map<String,Object> result=new HashMap<String,Object>();
		
		return result;
	}
	
}
