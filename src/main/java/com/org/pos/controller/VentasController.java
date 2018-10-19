package com.org.pos.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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

import com.org.pos.model.DetalleVenta;
import com.org.pos.model.Venta;
import com.org.pos.services.VentaService;


@Controller
public class VentasController {
	@Autowired
	VentaService ventasService;
	
	@PostMapping("/createVenta")
	public ResponseEntity<?> getMenu(@RequestBody LinkedHashMap<Object, ?> body,Principal principal) {
		
		Venta venta=new Venta();
		List<DetalleVenta> listaDetalleVentas=new ArrayList<DetalleVenta>();
		venta.setCambio(body.get("cambio")!=null ? Double.parseDouble(body.get("cambio").toString()) : 0.0);
		venta.setCliente_idcliente(body.get("cliente")!=null ? Integer.parseInt(body.get("cliente").toString()) : 0);
		venta.setConsecutivoVenta(0);
		venta.setEfectivoRecib(body.get("efectivo")!=null ? Double.parseDouble(body.get("efectivo").toString()) : 0.0);
		venta.setFechaVenta(new Date());
		venta.setIdVenta(0);
		venta.setTotal(body.get("total")!=null ? Double.parseDouble(body.get("total").toString()) : 0.0);
		venta.setUsuarios_idusuario(body.get("usuario")!=null ? Integer.parseInt(body.get("usuario").toString()): 1);
		List<Map<String,Object>> mapaDetalle=(ArrayList<Map<String,Object>>)body.get("listaDetalle");
		
		for(Map<String,Object> detalle:mapaDetalle) {
			DetalleVenta detalleVenta=new DetalleVenta();
			detalleVenta.setCantidadAgregada(Double.parseDouble(detalle.get("cantidadAgregada").toString()));
			detalleVenta.setCantidadOriginal(Double.parseDouble(detalle.get("cantidadOriginal").toString()));
			detalleVenta.setCantidadRestante(Double.parseDouble(detalle.get("cantidadRestante").toString()));
			detalleVenta.setDescripcionProd(detalle.get("descripcion").toString());
			detalleVenta.setPrecioTotal(Double.parseDouble(detalle.get("precio").toString()));
			detalleVenta.setProductos_idproductos(Integer.parseInt(detalle.get("idProducto").toString()));
			
			listaDetalleVentas.add(detalleVenta);
		}
		

		//Map<String,Object> myLinkedHashMap =(Map<String, Object>) body.get("listaDetalle");
		//Map<String,Object> myLinkedHashMap =  new LinkedHashMap<String, Object>();
		//myLinkedHashMap.put("map", cadena);
		//Map<String,Object> map = (Map) myLinkedHashMap.get("map");
		venta.setDetalleVenta(listaDetalleVentas);
		 
		Map<String,Object> flujoResult=ventasService.insertarVenta(principal,venta);
	
		return new ResponseEntity<Map<String,Object>>(flujoResult,HttpStatus.OK);
		
	}
	
	@GetMapping("/getVenta/{id}")
	public Object getToEvalById(@PathVariable Long id) {
		try {
			return new Object();
		}catch(Exception e) {
			return null;
		}
	}
	
	
	@PostMapping(value = "/getAllVentas")
	public @ResponseBody ResponseEntity<List<Map<String, Object>>> getAllVentas(@RequestParam String str, @RequestParam int type) {
		if(type == 0) {
			//return new ResponseEntity<List<Map<String, Object>>>(ticketRepository.getUserByName(str), HttpStatus.OK);
			return null;
		}else {
			//return new ResponseEntity<List<Map<String, Object>>>(ticketRepository.getUserByEmail(str), HttpStatus.OK);
			return null;
		}
		
	}
	
	@PutMapping(value = "/updateVenta/{id}")
	public Object updateVenta(@PathVariable Long id) {
		try {
			return new Object();
		}catch(Exception e) {
			return null;
		}
	}
	
	@DeleteMapping(value = "/deleteVenta/{id}")
	public Object deleteVenta(@PathVariable Long id) {
		try {
			//we dont delete producs to preserve the integrity of historical data
			//instead we only deactivate producs to not be showed in the list
			return new Object();
		}catch(Exception e) {
			return null;
		}
	}
}
