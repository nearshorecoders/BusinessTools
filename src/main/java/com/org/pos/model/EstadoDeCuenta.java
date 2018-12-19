package com.org.pos.model;

import java.util.Date;

public class EstadoDeCuenta {
	private Integer idEstadoDeCuenta;
	private Integer consecutivo;
	private Double cuentaTotal;
	private Double ingresoACuenta;
	private Double restante;
	private Double nuevoRestante;
	private Date fecha;
	private Integer idCliente;
	private Integer idProducto;
	private Integer idStatus;
	private String descripcionProducto;
	private String marcaProducto;
	private Integer estatusProducto;
	private String estatusProductoDescripcion;
	
	public Integer getIdEstadoDeCuenta() {
		return idEstadoDeCuenta;
	}
	public Double getCuentaTotal() {
		return cuentaTotal;
	}
	public Double getIngresoACuenta() {
		return ingresoACuenta;
	}
	public Double getRestante() {
		return restante;
	}
	public Double getNuevoRestante() {
		return nuevoRestante;
	}
	public Date getFecha() {
		return fecha;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdEstadoDeCuenta(Integer idEstadoDeCuenta) {
		this.idEstadoDeCuenta = idEstadoDeCuenta;
	}
	public void setCuentaTotal(Double cuentaTotal) {
		this.cuentaTotal = cuentaTotal;
	}
	public void setIngresoACuenta(Double ingresoACuenta) {
		this.ingresoACuenta = ingresoACuenta;
	}
	public void setRestante(Double restante) {
		this.restante = restante;
	}
	public void setNuevoRestante(Double nuevoRestante) {
		this.nuevoRestante = nuevoRestante;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Integer getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}
	public Integer getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public String getMarcaProducto() {
		return marcaProducto;
	}
	public void setMarcaProducto(String marcaProducto) {
		this.marcaProducto = marcaProducto;
	}
	public Integer getEstatusProducto() {
		return estatusProducto;
	}
	public void setEstatusProducto(Integer estatusProducto) {
		this.estatusProducto = estatusProducto;
	}
	public String getEstatusProductoDescripcion() {
		return estatusProductoDescripcion;
	}
	public void setEstatusProductoDescripcion(String estatusProductoDescripcion) {
		this.estatusProductoDescripcion = estatusProductoDescripcion;
	}
}
