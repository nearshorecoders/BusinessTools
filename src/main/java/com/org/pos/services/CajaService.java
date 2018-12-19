package com.org.pos.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pos.model.Retiro;
import com.org.pos.repository.CajaRepository;
import com.org.pos.repository.RetiroRepository;

@Service
public class CajaService {

	@Autowired
	CajaRepository cajaRepository;
	
	@Autowired
	RetiroRepository retiroRepository;
	
	public Map<String,Object> obtenerBalanceDelDia(String fi,String ff,Integer idSucursal){
		
		return cajaRepository.obtenerGastosDelDia(fi, ff, idSucursal);
	}
	
	public Map<String,Object> obtenerGastosDelDia(String fi,String ff,Integer idSucursal){
		
		return cajaRepository.obtenerSoloGastosDelDia(fi, ff, idSucursal);
	}
	
	public Map<String,Object> obtenerCortesDeCaja(String fi,String ff,Integer idSucursal){
		
		return cajaRepository.obtenerTodosLosCortesDeCaja(fi, ff, idSucursal);
	}
	
	public Retiro registrarRetiro(String cantidad,String descripcion,Integer idSucursal, String usuarioLogueado){  
		Double cantidadD=Double.parseDouble(cantidad);
		
		return retiroRepository.registrarRetiro(cantidadD, descripcion, idSucursal, usuarioLogueado);
	}
	
	
	public Integer realizarCorteDeCaja(Double balance,Integer idSucursal) {
		return cajaRepository.realizarCorteDeCaja(balance, idSucursal);
	}
}
