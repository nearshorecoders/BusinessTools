package com.org.pos.model;

import java.util.Date;
import java.util.List;

public class Venta {

	private Integer idVenta;
	private Double total;
	private Integer cliente_idcliente;
	private Integer usuarios_idusuario;
	private Date fechaVenta;
	private Integer consecutivoVenta;
	private Double efectivoRecib;
	private Double cambio;
	private List<DetalleVenta> detalleVenta;
	
	public Integer getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getCliente_idcliente() {
		return cliente_idcliente;
	}
	public void setCliente_idcliente(Integer cliente_idcliente) {
		this.cliente_idcliente = cliente_idcliente;
	}
	public Integer getUsuarios_idusuario() {
		return usuarios_idusuario;
	}
	public void setUsuarios_idusuario(Integer usuarios_idusuario) {
		this.usuarios_idusuario = usuarios_idusuario;
	}
	public Date getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public Integer getConsecutivoVenta() {
		return consecutivoVenta;
	}
	public void setConsecutivoVenta(Integer consecutivoVenta) {
		this.consecutivoVenta = consecutivoVenta;
	}
	public Double getEfectivoRecib() {
		return efectivoRecib;
	}
	public void setEfectivoRecib(Double efectivoRecib) {
		this.efectivoRecib = efectivoRecib;
	}
	public Double getCambio() {
		return cambio;
	}
	public void setCambio(Double cambio) {
		this.cambio = cambio;
	}
	public List<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}
	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}
	
	
}
