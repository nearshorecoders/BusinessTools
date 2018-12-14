package com.org.pos.model;

import java.util.Date;

public class ReporteVentas {
    private Integer consecutivoVenta;
	private Double  total;
	private Date fecha;
	private String descripcion;
	private String direccion;
	private Double precioTotal;
	private Double cantidadVendida;
	private Double subtotal;
	private String nombre;
	private String apellidop;
	private String apellidom;
	
	public Integer getConsecutivoVenta() {
		return consecutivoVenta;
	}
	public void setConsecutivoVenta(Integer consecutivoVenta) {
		this.consecutivoVenta = consecutivoVenta;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public Double getCantidadVendida() {
		return cantidadVendida;
	}
	public void setCantidadVendida(Double cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidop() {
		return apellidop;
	}
	public void setApellidop(String apellidop) {
		this.apellidop = apellidop;
	}
	public String getApellidom() {
		return apellidom;
	}
	public void setApellidom(String apellidom) {
		this.apellidom = apellidom;
	}
	
}
