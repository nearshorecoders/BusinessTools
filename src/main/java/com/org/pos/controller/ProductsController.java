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

import com.org.pos.model.Productos;
import com.org.pos.services.ProductosService;

@Controller
public class ProductsController {

	@Autowired
	ProductosService productosService;
	
	@PostMapping("/createProduct")
	public ResponseEntity<?> getMenu(@RequestBody Map<String, String> body,Principal principal) {
		Productos producto=new Productos();
		producto.setCodigo(body.get("codigo"));
		producto.setDescripcion("descripcion");
		producto.setEstatus(1);
		Double precioCompra=0.0;
		if(body.get("precioCompra")!=null) {
			precioCompra=Double.parseDouble(body.get("precioCompra"));
		}
		
		producto.setPrecioCompra(precioCompra);
		
		Double precioVenta=0.0;
		if(body.get("precioVenta")!=null) {
			precioVenta=Double.parseDouble(body.get("precioVenta"));
		}
		producto.setPrecioVenta(precioVenta);
		producto.setPresentacion(body.get("presentacion"));
		
		Double unidades=0.0;
		if(body.get("unidades")!=null) {
			unidades=Double.parseDouble(body.get("unidades"));
		}
		producto.setUnidadesEnCaja(unidades);
		
		
		producto.setUnidadMedida(body.get("unidadMedida"));
		
		 Map<String,Object> flujoResult=productosService.insertarProducto(principal,producto);
		return new ResponseEntity<Map<String,Object>>(flujoResult,HttpStatus.OK);
	}
	
	@GetMapping("/getProduct/{id}")
	public Object getToEvalById(@PathVariable Long id) {
		try {
			return new Object();
		}catch(Exception e) {
			return null;
		}
	}
	
	
	@PostMapping(value = "/getAllProducts")
	public @ResponseBody ResponseEntity<List<Map<String, Object>>> getAllProducts(@RequestParam String str, @RequestParam int type) {
		if(type == 0) {
			//return new ResponseEntity<List<Map<String, Object>>>(ticketRepository.getUserByName(str), HttpStatus.OK);
			return null;
		}else {
			//return new ResponseEntity<List<Map<String, Object>>>(ticketRepository.getUserByEmail(str), HttpStatus.OK);
			return null;
		}
		
	}
	
	@PutMapping(value = "/updateProduct/{id}")
	public Object updateProduct(@PathVariable Long id) {
		try {
			return new Object();
		}catch(Exception e) {
			return null;
		}
	}
	
	@DeleteMapping(value = "/deleteProduct/{id}")
	public Object delecteProduct(@PathVariable Long id) {
		try {
			//we dont delete producs to preserve the integrity of historical data
			//instead we only deactivate producs to not be showed in the list
			return new Object();
		}catch(Exception e) {
			return null;
		}
	}
}